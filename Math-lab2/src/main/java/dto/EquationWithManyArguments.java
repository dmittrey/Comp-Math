package dto;

import lombok.Data;

import java.util.List;
import java.util.function.Function;

@Data
public class EquationWithManyArguments {

    private Function<List<Double>, Double> mappingFunction;
}
