package dto;

import lombok.Data;

import java.util.function.DoubleFunction;

@Data
public class Equation implements Cloneable {

    private Slice slice;
    private double epsilon;
    private DoubleFunction<Double> mappingFunction;
    private DoubleFunction<Double> mappingWithoutXFunction;

    @Override
    public Equation clone() throws CloneNotSupportedException {
        Equation clone = (Equation) super.clone();
        clone.setEpsilon(epsilon);

        return clone;
    }
}
