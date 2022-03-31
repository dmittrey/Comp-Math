package dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleFunction;
import java.util.function.Function;

@Data
public class EquationSystem {

    private double epsilon;
    private List<EquationWithManyArguments> equationList;
    private Slice slice;

    public List<Function<List<Double>, Double>> getMappingWithoutXFunctions() {
        List<Function<List<Double>, Double>> result = new ArrayList<>();
        for (EquationWithManyArguments equation: equationList) {
            result.add(equation.getMappingWithoutXFunction());
        }
        return result;
    }
}
