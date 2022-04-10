package utility.resolvers;

import dto.EquationRoot;
import dto.Slice;
import lombok.extern.java.Log;
import dto.Equation;

import java.util.function.DoubleFunction;
import java.util.function.Function;

/**
 * Метод хорд
 */
@Log
public class SecantMethodResolver implements Function<Equation, EquationRoot> {

    @Override
    public EquationRoot apply(Equation equation) {

        Slice equationSlice = equation.getSlice();
        double previousCrossX;
        double crossX = 0;
        int counter = 0;

        do {
            previousCrossX = (counter++ != 0) ? crossX : equationSlice.getStop() + 2 * equation.getEpsilon();

            crossX = getCrossXValue(equation.getMappingFunction(), equationSlice);
            equationSlice = getUsefulSlice(new Slice(equationSlice.getStart(), crossX), new Slice(crossX, equationSlice.getStop()));
        } while (Math.abs(previousCrossX - crossX) > equation.getEpsilon());

        System.out.println("Secant method: x = " + crossX + " :: Count = " + counter);

        return new EquationRoot(crossX, counter);
    }

    private double getCrossXValue(DoubleFunction<Double> function, Slice equationSlice) {
        return equationSlice.getStart() - ((equationSlice.getStop() - equationSlice.getStart()) /
                (function.apply(equationSlice.getStop()) - function.apply(equationSlice.getStart()))) * (function.apply(equationSlice.getStart()));
    }

    private Slice getUsefulSlice(Slice firstSlice, Slice secondSlice) {
        return (firstSlice.isDifferentSignedEnds()) ? firstSlice : secondSlice;
    }
}
