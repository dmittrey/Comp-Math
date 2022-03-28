package dto;

import lombok.Data;

@Data
public class ReadEquation {
    private ReadStatus readStatus;
    private Equation equation;

    public boolean isSuccessful() {
        return readStatus.equals(ReadStatus.SUCCESSFUL);
    }
}
