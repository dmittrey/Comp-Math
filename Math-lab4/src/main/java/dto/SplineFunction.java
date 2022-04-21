package dto;

import lombok.Value;

import java.util.function.DoubleFunction;

@Value
public class SplineFunction {
    DoubleFunction<Double> ownFunction;
    Slice slice;

    public static SplineFunction getStartSplineFunction(CoefficientsSquad coefficients, Double xValue, Double xStart, Double xEnd) {
        return new SplineFunction(
                x -> coefficients.getA() +
                        coefficients.getB() * (x - xValue) +
                        coefficients.getC() * Math.pow((x - xValue), 2) +
                        coefficients.getD() * Math.pow((x - xValue), 3),
                new Slice(xStart, xEnd)
        );
    }
}
