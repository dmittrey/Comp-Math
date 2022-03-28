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

        //1) Прибавить коэф при x

        if (equation.getEquationCoefficients().size() > 1) {
            equation.getEquationCoefficients().set(1, equation.getEquationCoefficients().get(0) + 1);
        } else if (equation.getEquationCoefficients().size() == 1) {
            equation.getEquationCoefficients().add(1.);
        } else {
            equation.getEquationCoefficients().add(0.);
            equation.getEquationCoefficients().add(1.);
        }
        //2) Найти производную

        for (int i = equation.getEquationCoefficients().size() - 1; i > 0; i--) {
            equation.getEquationCoefficients().set(i - 1, i * equation.getEquationCoefficients().get(i));
        }
        //3) подставить границы в производную

        double startFunc = equation.getMappingFunction().apply(equation.getSlice().getStart());
        double stopFunc = equation.getMappingFunction().apply(equation.getSlice().getStop());

        //4) выбрать
        double lambda = fetchLambda(startFunc, stopFunc);

        double x = equation.getSlice().getStart();
        double previousX;
        int counter = 0;

        do {
            counter++;
            previousX = x;
            x = phi.apply(previousX);

            log.info("Counter: " + counter);
            log.info("previousX: " + previousX);
            log.info("xValue: " + x);
        } while (Math.abs(previousX - x) > 0);

        return new EquationRoot(x, equation.getMappingFunction().apply(x), counter);
    }

    private double fetchLambda(double startFunc, double stopFunc) {
        return (startFunc > stopFunc) ? -1 / startFunc : -1 / stopFunc;
    }
}