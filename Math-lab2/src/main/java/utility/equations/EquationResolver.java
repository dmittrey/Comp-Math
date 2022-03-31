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

    public List<EquationRoot> resolve(Equation equation) {
        ArrayList<EquationRoot> equationRoots = new ArrayList<>();
        resolveMethods
                .forEach(resolveMethod -> equationRoots.add(resolveMethod.apply(equation)));
        return equationRoots;
    }
}
