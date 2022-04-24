package utility.functionaliterface;

import dto.CoefficientsSquad;

@FunctionalInterface
public interface LinearFunction {
    double compute(CoefficientsSquad coefficientsSquad, Double xValue);
}
