package utility.equations;

import dto.Equation;
import lombok.Getter;

import java.util.function.Function;

@Getter
public enum EquationTypes {

    FIRST_EQUATION("x^3 - x + 4 = 0", EquationFactory::produceFirstEquation),
    SECOND_EQUATION("x^3 + 4*x - 3 = 0", EquationFactory::produceSecondEquation),
    THIRD_EQUATION("x^2 - 20 * sin(x) = 0", EquationFactory::produceThirdEquation);

    private final String description;
    private final Function<Equation, Equation> equationInitFunction;

    EquationTypes(String aDescription, Function<Equation, Equation> anEquationInitFunction) {
        description = aDescription;
        equationInitFunction = anEquationInitFunction;
    }


}
