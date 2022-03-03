package utility;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import matrix.Matrix;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Log
public class MatrixResolver {

    private final Matrix matrix;

    public Optional<Double[]> resolve() {

        logOwnMatrix();

        // Det finding
        double determinant = calculateDeterminant(matrix);
        log.info("Det: " + determinant);
        if (determinant == 0) return Optional.empty();

        logOwnMatrix();

        // Set to predominance of diagonal elements condition
        getPredominanceOfDiagonalElements();

        // Test predominance of diagonal elements condition
        if (!isPredominanceOfDiagonalElements()) return Optional.empty();

        logOwnMatrix();

        //Выразить переменные
        getXValues();

        logOwnMatrix();

        //Проверить условие сходимости

        //Сам способ итеративный

        return Optional.of(new Double[0]);

    }

    //todo:dmittrey cut this method
    private void logOwnMatrix() {
        Arrays.stream(matrix.getMatrixValues()).forEach(val -> log.info(Arrays.toString(val)));
    }

    private boolean isPredominanceOfDiagonalElements() {
        for (int i = 0; i < matrix.getColumnSize(); i++) {

            double rowSum = Arrays.stream(matrix.getMatrixValues()[i])
                    .map(Math::abs)
                    .reduce(0., Double::sum);

            for (int j = 0; j < matrix.getRowSize(); j++) {

                double currentElement = matrix.getMatrixValues()[i][j];

                if (Math.abs(currentElement) >= rowSum - Math.abs(currentElement) && i != j) return false;
            }
        }

        return true;
    }

    /**
     * У нас массив строк
     * <p>
     * Делаем так, чтобы выполнялось условие преобладания диагональных элементов
     */
    private void getPredominanceOfDiagonalElements() {

        for (int i = 0; i < matrix.getColumnSize(); i++) {

            double largestRowElement = matrix.getMatrixValues()[i][i];
            int largestRowNumber = i;

            for (int j = i; j < matrix.getColumnSize(); j++) {

                if (matrix.getMatrixValues()[j][i] > largestRowElement) {
                    largestRowElement = matrix.getMatrixValues()[j][i];
                    largestRowNumber = j;
                }
            }

            if (largestRowNumber != i) swapRows(i, largestRowNumber);
        }
    }

    private void swapRows(int fromRow, int toRow) {
        for (int i = 0; i < matrix.getRowSize(); i++) {
            double buffer = matrix.getMatrixValues()[fromRow][i];
            matrix.getMatrixValues()[fromRow][i] = matrix.getMatrixValues()[toRow][i];
            matrix.getMatrixValues()[toRow][i] = buffer;
        }
    }

    private double calculateDeterminant(Matrix srcMatrix) {

        Matrix matrixCopy = srcMatrix.getCopy();

        double determinant = 1.;

        setToTriangleView(matrixCopy);
        for (int i = 0; i < matrixCopy.getColumnSize(); i++) {
            determinant *= matrixCopy.getMatrixValues()[i][i];
        }

        return determinant;
    }

    private void setToTriangleView(Matrix matrixCopy) {

        for (int i = 0; i < matrixCopy.getColumnSize(); i++) {

            double hostElement = matrixCopy.getMatrixValues()[i][i];
            for (int j = i + 1; j < matrixCopy.getColumnSize(); j++) {
                double currentElement = matrixCopy.getMatrixValues()[j][i];
                double k = currentElement / hostElement;

                double[] newHostRow = multipleRow(matrixCopy, i, k);
                subRows(matrixCopy, newHostRow, j);
            }
        }
    }

    private double[] multipleRow(Matrix matrixCopy, int rowNumber, double k) {
        double[] resultRow = new double[matrixCopy.getRowSize()];

        for (int i = 0; i < matrixCopy.getRowSize(); i++) {
            resultRow[i] = k * matrixCopy.getMatrixValues()[rowNumber][i];
        }

        return resultRow;
    }

    private void subRows(Matrix copyMatrix, double[] hostRow, int currentRow) {
        for (int i = 0; i < copyMatrix.getRowSize(); i++) {
            copyMatrix.getMatrixValues()[currentRow][i] =
                    copyMatrix.getMatrixValues()[currentRow][i] - hostRow[i];
        }
    }

    public void getXValues() {
        for (int i = 0; i < matrix.getColumnSize(); i++) {
            divRow(i, -1 * matrix.getMatrixValues()[i][i]);
            matrix.getMatrixValues()[i][i] = 0.;
        }
    }

    private void divRow(int rowNumber, double k) {
        for (int i = 0; i < matrix.getRowSize(); i++) {
            matrix.getMatrixValues()[rowNumber][i] /= k;
        }
    }
}
