package dto.input;

import input.ReadStatus;
import lombok.Value;
import utility.Correctness;

@Value
public class InputResult<T> implements Correctness {
    ReadStatus readStatus;
    T readValue;

    public static <T> InputResult<T> getIncorrectInputResult(ReadStatus readStatus) {
        return new InputResult<>(readStatus, null);
    }

    @Override
    public boolean isIncorrect() {
        return readStatus != ReadStatus.SUCCESSFUL;
    }
}
