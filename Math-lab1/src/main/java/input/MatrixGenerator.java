package input;

import lombok.Setter;
import matrix.Matrix;

import java.util.Scanner;

public class MatrixGenerator implements DataReader {

    @Setter
    private Scanner scanner;

    @Override
    public Matrix readData() {
        Matrix matrix = new Matrix();
        matrix.setMatrixValues(getGeneratedMatrixValues());

        int matrixSizes = getMatrixSizes();
        matrix.setRowSize(matrixSizes);
        matrix.setColumnSize(matrixSizes);

        return matrix;
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

    private int getMatrixSizes() {

    }

    private double[][] getGeneratedMatrixValues() {

    }
}
