package input;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import matrix.Matrix;

import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
public abstract class DataReader {

    @Setter
    @Getter
    private Scanner scanner;

    public Optional<Matrix> readData() {
        Matrix matrix = new Matrix();

        Optional<Double> optionalEpsilon = getEpsilon();
        if (!optionalEpsilon.isPresent()) return Optional.empty();
        matrix.setEpsilon(optionalEpsilon.get());

        Optional<Integer> optionalSizes = getMatrixSizes();
        if (!optionalSizes.isPresent()) return Optional.empty();
        matrix.setColumnSize(optionalSizes.get());
        matrix.setRowSize(optionalSizes.get());

        Optional<Double[][]> optionalMatrixValues = getMatrixValues(optionalSizes.get());
        if (!optionalMatrixValues.isPresent()) return Optional.empty();

//        Arrays.stream(optionalMatrixValues.get()).forEach(val -> log.info(Arrays.toString(val)));

        matrix.setMatrixValues(optionalMatrixValues.get());

        return Optional.of(matrix);
    }

    public Optional<Double> getEpsilon() {
        while (true) {
            System.out.print("Write epsilon: ");

            String answer = scanner.next();
            try {
                return Optional.of(Double.parseDouble(answer));
            } catch (NumberFormatException ignored) {
                // Statement to continue while
            }
        }
    }

    public Optional<Double[]> getRow(Scanner openedFileScanner, int rowSize) {

        String answer = (openedFileScanner.hasNextLine()) ? openedFileScanner.nextLine() : "";

        Double[] resultRow = new Double[rowSize];
        Matcher m = Pattern.compile("\\d+").matcher(answer);

        int i = 0;
        while (m.find()) {
            resultRow[i++] = Double.parseDouble(m.group());
        }

        if (i == rowSize) return Optional.of(resultRow);
        else return Optional.empty();
    }

    public void getRequiredData(Scanner scanner) {
        setScanner(scanner);
    }

    protected abstract Optional<Double[][]> getMatrixValues(int matrixSize);

    protected abstract Optional<Integer> getMatrixSizes();
}
