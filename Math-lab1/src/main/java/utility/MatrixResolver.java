package utility;

import lombok.RequiredArgsConstructor;
import matrix.Matrix;

import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class MatrixResolver {

    private final Matrix matrix;

    public double[] resolve() throws IOException {
        // Привели к условию преобладания диагональных элементов
        getPredominanceOfDiagonalElements();

        //Тут надо сам способ

        return new double[0];

    }

    /**
     * У нас массив строк
     */
    private void getPredominanceOfDiagonalElements() throws IOException {

        for (int i = 0; i < matrix.getColumnSize(); i++) {

            for (int j = 0; j < matrix.getColumnSize(); j++) {

                double[] row = matrix.getMatrixValues()[i];

                int largestRowNumber = getLargestMember(row);

                if (largestRowNumber == i) swapRows(i, j);
            }
        }
    }

    private int getLargestMember(double[] row) throws IOException {

        double sumOfAll = Arrays.stream(row).map(Math::abs).sum();

        for (int j = 0; j < matrix.getColumnSize(); j++) {
            if (Math.abs(row[j]) < sumOfAll - Math.abs(row[j])) return j;
        }

        throw new IOException("Unreached Statement");
    }

    private void swapRows(int fromRow, int toRow) {
        for (int i = 0; i < matrix.getRowSize(); i++) {
            double buffer = matrix.getMatrixValues()[fromRow][i];
            matrix.getMatrixValues()[fromRow][i] = matrix.getMatrixValues()[toRow][i];
            matrix.getMatrixValues()[toRow][i] = buffer;
        }
    }


}
