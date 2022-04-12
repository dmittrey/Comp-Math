package input;

import dto.input.InputResult;

import java.util.List;

public interface DataReader {

    InputResult<Double> readEpsilon();

    InputResult<Double> readSliceCount();

    InputResult<List<Double>> readSlicePoints();
}
