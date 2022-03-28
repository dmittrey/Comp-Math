package input;

import dto.ReadEquation;
import dto.ReadStatus;
import lombok.Getter;
import lombok.Setter;
import utility.OutputFormatter;

import java.util.Optional;
import java.util.Scanner;

public abstract class DataReader {

    @Setter
    @Getter
    private Scanner scanner;

    @Setter
    @Getter
    private OutputFormatter outputFormatter;

    public ReadEquation readData() {

        ReadEquation readEquation = new ReadEquation();
        readEquation.setReadStatus(ReadStatus.EPSILON_NOT_FOUND);
        getEpsilon().ifPresent(epsilon -> {
            readEquation.getEquation().setEpsilon(epsilon);
            readEquation.setReadStatus(ReadStatus.EQUATION_COEFFICIENTS_NOT_FOUND);
            getEquationCoefficients().ifPresent(equationCoefficients -> {
                readEquation.getEquation().setEquationCoefficients(equationCoefficients);
                readEquation.setReadStatus(ReadStatus.SUCCESSFUL);
            });
        });

        return readEquation;
    }

    public Optional<Double> getEpsilon() {
        outputFormatter.write("Write epsilon: ");

        return (scanner.hasNextDouble()) ? Optional.of(scanner.nextDouble()) : Optional.empty();
    }

    public void getRequiredData(Scanner scanner, OutputFormatter outputFormatter) {
        setScanner(scanner);
        setOutputFormatter(outputFormatter);
    }

    protected abstract Optional<Double[]> getEquationCoefficients();
}
