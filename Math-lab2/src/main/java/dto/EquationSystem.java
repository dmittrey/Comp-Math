package dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Data
public class EquationSystem {

    private double epsilon;
    private List<EquationWithManyArguments> equationList;
    private List<Double> firstVariables;

    public List<Function<List<Double>, Double>> getMappingFunctions() {
        List<Function<List<Double>, Double>> result = new ArrayList<>();
        for (EquationWithManyArguments equation: equationList) {
            result.add(equation.getMappingFunction());
        }
        return result;
    }
}
