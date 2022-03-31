package utility.resolvers;

import dto.Equation;
import dto.EquationRoot;
import dto.Slice;
import lombok.extern.java.Log;

import java.util.function.DoubleFunction;
import java.util.function.Function;

/**
 * Метод хорд
 */
@Log
public class SecantMethodResolver implements Function<Equation, EquationRoot> {

    @Override
    public EquationRoot apply(Equation equation) {

        log.info("Equation" + equation.toString());

        Slice equationSlice = equation.getSlice();
        double previousCrossX;
        double crossX = 0;
        double crossY;
        int counter = 0;

        do {
            previousCrossX = (counter++ != 0) ? crossX : equationSlice.getStop() + 2 * equation.getEpsilon();

            crossX = getCrossXValue(equation.getMappingFunction(), equationSlice);
            crossY = equation.getMappingFunction().apply(crossX);
            equationSlice = getUsefulSlice(new Slice(equationSlice.getStart(), crossX), new Slice(crossX, equationSlice.getStop()));
//
//            log.info("previousCrossX" + previousCrossX);
//            log.info("crossX" + crossX);
//            log.info("crossY" + crossY);
//            log.info("equationSlice" + equationSlice);
        } while (Math.abs(previousCrossX - crossX) > equation.getEpsilon());

        return new EquationRoot(crossX, crossY, counter);
    }

    private double getCrossXValue(DoubleFunction<Double> function, Slice equationSlice) {
        return equationSlice.getStart() - ((equationSlice.getStop() - equationSlice.getStart()) /
                (function.apply(equationSlice.getStop()) - function.apply(equationSlice.getStart()))) * (function.apply(equationSlice.getStart()));
    }

    private Slice getUsefulSlice(Slice firstSlice, Slice secondSlice) {
        return (firstSlice.isDifferentSignedEnds()) ? firstSlice : secondSlice;
    }
}
