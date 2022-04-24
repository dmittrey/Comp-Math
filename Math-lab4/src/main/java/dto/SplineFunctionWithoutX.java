package dto;

import lombok.Value;

import java.util.function.BiFunction;
import java.util.function.Function;

@Value
public class SplineFunctionWithoutX {
    Function<CoefficientsSquad, Double> ownFunction;
    Function<CoefficientsSquad, Double> ownFunctionFirstDerivative;
    Function<CoefficientsSquad, Double> ownFunctionSecondDerivative;

    public static SplineFunctionWithoutX insertX(SplineFunction splineFunction, double xValue) {
        return new SplineFunctionWithoutX(
                cuff -> splineFunction.getOwnFunction().apply(cuff, xValue),
                cuff -> splineFunction.getOwnFunctionFirstDerivative().apply(cuff, xValue),
                cuff -> splineFunction.getOwnFunctionSecondDerivative().apply(cuff, xValue)
        );
    }
}
