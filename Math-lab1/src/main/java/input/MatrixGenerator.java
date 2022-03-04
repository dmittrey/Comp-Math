package input;

import java.util.Optional;

public class MatrixGenerator extends DataReader {


    @Override
    protected Optional<Double[][]> getMatrixValues(int matrixSize) {
        Double[][] result = new Double[matrixSize][matrixSize + 1];

        for (int i = 0; i < matrixSize; i++) {
            double delta = 1.;

            for (int j = 0; j < matrixSize; j++) {
                result[i][j] = Math.random();
                if (i == j) result[i][j] = 0.;

                if (delta - result[i][j] < 0) {
                    result[i][j] = 0.;
                } else delta -= result[i][j];
            }

            result[i][matrixSize] = Math.random() * 20;
        }

        super.getOutputFormatter().printMatrixValues(result);

        return Optional.of(result);
    }

    @Override
    protected Optional<Integer> getMatrixSizes() {

        return Optional.of(20);
    }
}
