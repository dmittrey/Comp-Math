package utility.equations;

import dto.Equation;
import dto.EquationSystem;
import dto.EquationWithManyArguments;

import java.util.ArrayList;

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
        equation.setMappingFunction(x -> Math.pow(x, 3) + 4 * x - 3);
        equation.setMappingWithoutXFunction(x -> (-1. / 16. * Math.pow(x, 3)) + (3. / 4. * x) + (3. / 16.));
        return equation;
    }

    //Math.pow(x, 2) - 20 * Math.sin(x);
    public static Equation produceThirdEquation(Equation equation) {
        equation.setMappingFunction(x -> Math.pow(x, 2) - 20 * Math.sin(x));
        equation.setMappingWithoutXFunction(x -> x + ((Math.pow(x, 2) - 20 * Math.sin(x)) / (4 + 20 * Math.cos(-2))));
        return equation;
    }

    public static EquationWithManyArguments getFirstEquationInSystem() {
        EquationWithManyArguments equation = new EquationWithManyArguments();
        equation.setMappingFunction(list -> Math.pow(list.get(0), 2) + Math.pow(list.get(1), 2) - 1);
        return equation;
    }

    public static EquationWithManyArguments getSecondEquationInSystem() {
        EquationWithManyArguments equation = new EquationWithManyArguments();
        equation.setMappingFunction(list -> Math.pow(list.get(0), 3) + list.get(1));
        return equation;
    }

    public static EquationSystem getSystem() {
       EquationSystem equationSystem = new EquationSystem();
       equationSystem.setEquationList(new ArrayList<>());
       equationSystem.getEquationList().add(getFirstEquationInSystem());
       equationSystem.getEquationList().add(getSecondEquationInSystem());
       return equationSystem;
    }
}
