package utility.equations;

import dto.Equation;

public class EquationFactory {

    private EquationFactory() {

    }

    //x^3 - x + 4 = 0
    public static Equation produceFirstEquation(Equation equation) {
        equation.setMappingFunction(x -> Math.pow(x, 3) - x + 4);
        equation.setMappingWithoutXFunction(x -> (12. / 11. * x) - (1. / 11. * Math.pow(x, 3)) - (4. / 11.));
        return equation;
    }

    //Math.pow(x, 3) + 4 * x - 3;
    public static Equation produceSecondEquation(Equation equation) {
        equation.setMappingFunction(x -> Math.pow(x, 3) - x + 4);
        equation.setMappingWithoutXFunction(x -> (12 / 11 * x) - (1 / 11 * Math.pow(x, 3)) - (4 / 11));
        return equation;
    }

    //Math.pow(x, 2) - 20 * Math.sin(x);
    public static Equation produceThirdEquation(Equation equation) {
        equation.setMappingFunction(x -> Math.pow(x, 3) - x + 4);
        equation.setMappingWithoutXFunction(x -> (12 / 11 * x) - (1 / 11 * Math.pow(x, 3)) - (4 / 11));
        return equation;
    }

    //Math.sqrt(x) - (1 / x)
    public static Equation produceFourthEquation(Equation equation) {
        equation.setMappingFunction(x -> Math.pow(x, 3) - x + 4);
        equation.setMappingWithoutXFunction(x -> (12 / 11 * x) - (1 / 11 * Math.pow(x, 3)) - (4 / 11));
        return equation;
    }
}
