package input;

import dto.input.InputResult;

import java.util.List;

public interface DataReader {

    InputResult<Double> readEpsilon();

    InputResult<Integer> readFunctionChoice(int countOfFunctions);

    InputResult<List<Double>> readSlicePoints();
}
