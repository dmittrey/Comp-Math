package input;

import dto.input.InputResult;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class Console {

    private final Scanner scanner;

    public InputResult<Double> readIntOrDouble() {
        if (scanner.hasNext("\\d+(\\.\\d+)?")) {
            double nextValue = Double.parseDouble(scanner.next("\\d+(\\.\\d+)?"));
            return new InputResult<>(ReadStatus.SUCCESSFUL, nextValue);
        } else
            return InputResult.getIncorrectInputResult(ReadStatus.END_OF_STREAM);
    }

    public InputResult<Integer> readIntInRange(int startValue, int endValue) {
        while (true){
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value >= startValue && value <= endValue) return new InputResult<>(ReadStatus.SUCCESSFUL, scanner.nextInt());
            } else
                return InputResult.getIncorrectInputResult(ReadStatus.END_OF_STREAM);
        }
    }

    public InputResult<List<Double>> readTwoDoubles() {
        InputResult<Double> firstInputResult = readIntOrDouble();
        InputResult<Double> secondInputResult = readIntOrDouble();

        if (firstInputResult.isIncorrect() || secondInputResult.isIncorrect())
            return InputResult.getIncorrectInputResult(ReadStatus.END_OF_STREAM);
        else
            return new InputResult<>(ReadStatus.SUCCESSFUL, List.of(firstInputResult.getReadValue(), secondInputResult.getReadValue()));
    }
}
