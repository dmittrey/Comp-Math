package input;

import dto.input.InputResult;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class IOReader implements DataReader {

    private final Console console;

    @Override
    public InputResult<Double> readEpsilon() {
        return console.readDouble();
    }

    @Override
    public InputResult<Double> readSliceCount() {
        return console.readDouble();
    }

    @Override
    public InputResult<List<Double>> readSlicePoints() {
        return console.readTwoDoubles();
    }
}