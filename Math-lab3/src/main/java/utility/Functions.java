package utility;

import java.util.function.DoubleFunction;

public enum Functions implements DoubleFunction<Double> {

    FUNCTION_ONE(value -> Math.exp(Math.sin(value))),
    FUNCTION_TWO(value -> {
        if (value == 2) return 4.;
        else return 2.;
    }),
    FUNCTION_THREE(value -> Math.abs(1 / (value - 2)));

    private final DoubleFunction<Double> function;

    Functions(DoubleFunction<Double> function) {
        this.function = function;
    }

    @Override
    public Double apply(double value) {
        return function.apply(value);
    }
}
