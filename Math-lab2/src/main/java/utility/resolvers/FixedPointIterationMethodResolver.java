package utility.resolvers;


import dto.Equation;
import dto.EquationRoot;
import lombok.extern.java.Log;

import java.util.function.Function;

/**
 * Метод простых итераций
 */
@Log
public class FixedPointIterationMethodResolver implements Function<Equation, EquationRoot> {

    /**
     * 1) Выражаем x
     * 2) Берем x_0 любую точку между
     * 3) Итерируемся по ней
     */
    @Override
    public EquationRoot apply(Equation equation) {
        return null;
    }
}
