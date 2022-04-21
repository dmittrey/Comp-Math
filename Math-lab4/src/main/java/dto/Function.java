package dto;

import lombok.Value;

import java.util.function.DoubleFunction;

@Value
public class Function {
    DoubleFunction<Double> ownFunction;
    Slice slice;
}
