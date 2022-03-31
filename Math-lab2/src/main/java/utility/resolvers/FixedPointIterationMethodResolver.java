package utility.resolvers;

import dto.Equation;
import dto.EquationRoot;
import lombok.extern.java.Log;

import java.util.function.DoubleFunction;
import java.util.function.Function;

/**
 * Метод простых итераций
 */
@Log
public class FixedPointIterationMethodResolver implements Function<Equation, EquationRoot> {

    @Override
    public EquationRoot apply(Equation equation) {

        DoubleFunction<Double> phi = equation.getMappingWithoutXFunction();
        double previousX;
        double newX = equation.getSlice().getStart();
        int count = 0;

        do {
            count++;
            previousX = newX;
            newX = phi.apply(previousX);
//            log.info("Count " + count + " :: previousX " + previousX + " :: newX " + newX);
        } while (isIterationNotEnd(previousX, newX, equation.getEpsilon()));

        return new EquationRoot(newX, equation.getMappingFunction().apply(newX), count);
    }

    private boolean isIterationNotEnd(double previousX, double newX, double epsilon) {
        return Math.abs(newX - previousX) > epsilon;
    }
}