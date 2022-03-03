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

        // Get values
        getXValues();

        logOwnMatrix();

        //Проверить условие сходимости
        if (!isConvergenceCondition()) return Optional.empty();

        Double[] freeElements = getFreeElements();
        Double[] previousIterationElements = getFreeElements();
        int iterationNumber = 1;

        //Сам способ итеративный
        while (calculateAnswer(matrix.getMatrixValues(), previousIterationElements, freeElements, matrix.getEpsilon(), iterationNumber)) {
            iterationNumber += 1;
        }
        
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
        matrix.getMatrixValues()[rowNumber][matrix.getRowSize()] /= Math.abs(k);
    }

    private boolean isConvergenceCondition() {
        return (getMaxAbsRowSum() < 1);
    }

    private double getMaxAbsRowSum() {
        double maxResult = 0;

        for (int i = 0; i < matrix.getColumnSize(); i++) {
            double maxAbsRowSum = getAbsRowSum(i);

            if (maxResult == 0 && maxResult <= maxAbsRowSum) maxResult = maxAbsRowSum;
        }

        return maxResult;
    }

    private double getAbsRowSum(int rowNumber) {
        double result = 0;

        for (int i = 0; i < matrix.getRowSize(); i++) {
            result += Math.abs(matrix.getMatrixValues()[rowNumber][i]);
        }

        return result;
    }

    private Double[] getFreeElements() {
        Double[] freeElements = new Double[matrix.getColumnSize()];

        for (int i = 0; i < matrix.getColumnSize(); i++) {
            freeElements[i] = matrix.getMatrixValues()[i][matrix.getRowSize()];
        }

        return freeElements;
    }

    public boolean calculateAnswer(Double[][] coefficientMatrix, Double[] previousIteration, Double[] freeCoefficient, double epsilon, int iterationNumber) {
        double resultEpsilon = Double.MIN_VALUE;
        double[] result = new double[previousIteration.length];
        for (int i = 0; i < coefficientMatrix.length; i++) {
            result[i] = 0;
            for (int j = 0; j < coefficientMatrix.length; j++) {
                result[i] += coefficientMatrix[i][j] * previousIteration[j];
            }
            result[i] += freeCoefficient[i];
        }
        for (int i = 0; i < result.length; i++) {
            if (Math.abs(result[i] - previousIteration[i]) > resultEpsilon) {
                resultEpsilon = Math.abs(result[i] - previousIteration[i]);
            }
            previousIteration[i] = result[i];
        }
        printFormatAnswer(result, resultEpsilon);
        if (resultEpsilon > epsilon) {
            return true;
        } else {
            printAnswer(result, resultEpsilon, iterationNumber);
            return false;
        }
    }

    public void printFormatAnswer(double[] answers, double epsilon) {
        for (double answer : answers) {
            System.out.format(" %f", answer);
        }
        System.out.format(" %f", epsilon);
        System.out.println();
        System.out.println("-----------------------------");
    }

    public void printAnswer(double[] result, double epsilon, int iterationNumber) {
        System.out.println("Ответ:");
        for (int i = 1; i <= result.length; i++) {
            System.out.println("x" + i + " = " + result[i - 1]);
        }
        System.out.println("Конечная точность: " + epsilon);
        System.out.println("Номер итерации: " + iterationNumber);
    }
}
