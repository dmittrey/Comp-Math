package dto;

import lombok.Data;

import java.util.List;

@Data
public class ReadEquation {
    private ReadStatus readStatus;
    private Equation equation;
    private List<Double> variables;

    public ReadEquation() {
        this.equation = new Equation();
    }

    public boolean isSuccessful() {
        return readStatus.equals(ReadStatus.SUCCESSFUL);
    }
}
