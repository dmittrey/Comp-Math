package input;

import lombok.Setter;
import matrix.Matrix;

import java.util.Scanner;

public class UserIO implements DataReader {

    @Setter
    private Scanner scanner;

    @Override
    public Matrix readData() {
        Matrix matrix = new Matrix();
        matrix.setMatrixValues(getMatrixValues());

        int sizes = getMatrixSizes();
        matrix.setColumnSize(sizes);
        matrix.setRowSize(sizes);

        return new Matrix();
    }

    @Override
    public void getRequiredData(Scanner scanner) {
        setScanner(scanner);
    }

    @Override
    public double getEpsilon() {
        double answerNumber;

        while (true) {
            System.out.println("Write epsilon: ");

            String answer = scanner.next();
            try {
                answerNumber = Double.parseDouble(answer);
            } catch (NumberFormatException ignored) {
                continue;
            }

            return answerNumber;
        }
    }

    private double[][] getMatrixValues() {

    }

    private int getMatrixSizes() {

    }
}
