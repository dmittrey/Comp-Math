package input;

import dto.ReadEquation;
import dto.ReadStatus;
import dto.Slice;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import utility.OutputFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
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
            readEquation.setReadStatus(ReadStatus.SLICE_NOT_FOUND);
            getSlice().ifPresent(slice -> {
                readEquation.getEquation().setSlice(slice);
                getEquation(readEquation);
                readEquation.setReadStatus(ReadStatus.SUCCESSFUL);
            });
        });

        return readEquation;
    }

    public ReadEquation readWithoutEquationsData() {

        ReadEquation readEquation = new ReadEquation();
        readEquation.setReadStatus(ReadStatus.EPSILON_NOT_FOUND);
        getEpsilon().ifPresent(epsilon -> {
            readEquation.getEquation().setEpsilon(epsilon);
            readEquation.setReadStatus(ReadStatus.SLICE_NOT_FOUND);
            getFirstVariables().ifPresent(variables -> {
                readEquation.setVariables(variables);
                readEquation.setReadStatus(ReadStatus.SUCCESSFUL);
            });
        });

        return readEquation;
    }

    public Optional<Double> getEpsilon() {
        outputFormatter.write("Write epsilon: ");

        return (scanner.hasNextDouble()) ? Optional.of(scanner.nextDouble()) : Optional.empty();
    }

    public Optional<Slice> getSlice() {

        double start;
        double stop;

        outputFormatter.write("Enter start of slice: ");
        if (scanner.hasNextDouble()) start = scanner.nextDouble();
        else if (scanner.hasNextInt()) start = scanner.nextInt();
        else return Optional.empty();

        outputFormatter.write("Enter end of slice: ");
        if (scanner.hasNextDouble()) stop = scanner.nextDouble();
        else if (scanner.hasNextInt()) stop = scanner.nextInt();
        else return Optional.empty();

        return Optional.of(new Slice(start, stop));
    }

    public Optional<List<Double>> getFirstVariables() {

        getOutputFormatter().write("Write first variables: ");

        List<Double> allMatches = new ArrayList<>();

        while (getScanner().hasNextLine()) {
            Matcher m = Pattern.compile("-?\\d+(\\.\\d+)?")
                    .matcher(getScanner().nextLine());
            while (m.find()) {
                allMatches.add(Double.parseDouble(m.group()));
            }

            if (!allMatches.isEmpty()) {
                return Optional.of(allMatches);
            }
        }

        return Optional.empty();
    }

    public void getRequiredData(Scanner scanner, OutputFormatter outputFormatter) {
        setScanner(scanner);
        setOutputFormatter(outputFormatter);
    }

    protected abstract void getEquation(ReadEquation readEquation);
}
