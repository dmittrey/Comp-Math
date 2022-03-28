package utility.resolvers;

import dto.EquationRoot;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import dto.Equation;

import java.util.Arrays;
import java.util.function.Function;

/**
 * Метод хорд
 */
@Log
public class SecantMethodResolver implements Function<Equation, EquationRoot> {

    /**
     * 0) Находим интервал изоляции корня
     * 1) Вычисляем x_0(см презу)
     * 2) Вычисляем f(x_0)
     * 3) В качестве нового интервала выбираем ту половину отрезка, на концах которого
     * функция имеет разные знаки: [a_0,x_0] либо [b_0,x_0]
     * 4) Вычисляем 𝑥_1 и т.д (повторяем 1-3 шаги).
     * <p>
     * Критерий окончания итерационного процесса когда разницу между (x_n-x_n-1) будет меньше E
     */
    @Override
    public EquationRoot apply(Equation equation) {
        return null;
    }
}
