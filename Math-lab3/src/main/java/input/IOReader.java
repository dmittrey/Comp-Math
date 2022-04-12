package input;

import dto.input.InputResult;
import lombok.RequiredArgsConstructor;
import utility.outputformatting.FormattingWriter;

import java.util.List;

@RequiredArgsConstructor
public class IOReader implements DataReader {

    private final Console console;
    private final FormattingWriter writer;

    @Override
    public InputResult<Double> readEpsilon() {
        writer.printEpsilonReadRequest();
        return console.readIntOrDouble();
    }

    @Override
    public InputResult<Integer> readFunctionChoice(int countOfFunctions) {
        writer.printFunctionsForChoice();
        return console.readIntInRange(1, countOfFunctions);
    }

    @Override
    public InputResult<List<Double>> readSlicePoints() {
        writer.printSliceReadRequest();
        return console.readTwoDoubles();
    }
}
