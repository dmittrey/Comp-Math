package utility.equations;

import dto.Equation;
import dto.EquationRoot;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class EquationResolver {

    @Getter
    private final ArrayList<Function<Equation, EquationRoot>> resolveMethods = new ArrayList<>();

    public void addResolveMethod(Function<Equation, EquationRoot> resolveMethod) {
        resolveMethods.add(resolveMethod);
    }

    public void resolve(Equation equation) {
        resolveMethods
                .forEach(resolveMethod -> resolveMethod.apply(equation));
    }
}
