package utility.storage;

import java.util.function.DoubleFunction;

public enum Functions implements DoubleFunction<Double> {

    FUNCTION_ONE(value -> Math.exp(Math.sin(value)), "e^(sin(x))"),
    FUNCTION_TWO(value -> {
        if (value == 2) return 4.;
        else return 2.;
    }, """
            x = 2, f(x) = 4,
            x>2, x<2, f(x) = 2"""),
    FUNCTION_THREE(value -> Math.abs(1 / (value - 2)), "|1/(x-2)|");

    private final DoubleFunction<Double> function;
    private final String description;

    Functions(DoubleFunction<Double> function, String description) {
        this.function = function;
        this.description = description;
    }

    @Override
    public Double apply(double value) {
        return function.apply(value);
    }

    @Override
    public String toString() {
        return description;
    }
}
