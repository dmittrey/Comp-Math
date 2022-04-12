package dto.input;

import input.ReadStatus;
import lombok.Value;

import java.util.function.Consumer;

@Value
public class InputResult<T> {
    ReadStatus readStatus;
    T readValue;

    public static <T> InputResult<T> getIncorrectInputResult(ReadStatus readStatus) {
        return new InputResult<>(readStatus, null);
    }

    public boolean isIncorrect() {
        return readStatus != ReadStatus.SUCCESSFUL;
    }

    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if (isIncorrect()) {
            emptyAction.run();
        } else {
            action.accept(readValue);
        }
    }
}
