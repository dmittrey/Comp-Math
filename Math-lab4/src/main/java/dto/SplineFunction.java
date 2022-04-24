package dto;

import lombok.Value;

import java.util.function.BiFunction;

@Value
public class SplineFunction {
    BiFunction<CoefficientsSquad, Double, Double> ownFunction;
    BiFunction<CoefficientsSquad, Double, Double> ownFunctionFirstDerivative;
    BiFunction<CoefficientsSquad, Double, Double> ownFunctionSecondDerivative;
    Slice slice;

    public static SplineFunction getStartSplineFunction(Double xValue, Double xEnd) {
        return new SplineFunction(
                (cuffs, x) -> cuffs.getA() +
                        cuffs.getB() * (x - xValue) +
                        cuffs.getC() * Math.pow((x - xValue), 2) +
                        cuffs.getD() * Math.pow((x - xValue), 3),
                (cuffs, x) -> cuffs.getB() +
                        2 * cuffs.getC() * (x - xValue) +
                        3 * cuffs.getD() * (x - xValue),
                (cuffs, x) -> 2 * cuffs.getC() +
                        6 * cuffs.getD() * (x - xValue),
                new Slice(xValue, xEnd)
        );
    }
}
