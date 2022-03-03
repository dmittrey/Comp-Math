package input;

import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.Optional;

@Log
public class MatrixGenerator extends DataReader {


    @Override
    protected Optional<Double[][]> getMatrixValues(int matrixSize) {
        Double[][] result = new Double[matrixSize][matrixSize + 1];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize + 1; j++) {
                result[i][j] = Math.random() * 100;
            }
        }

        Arrays.stream(result).forEach(
                val -> log.info("Row: " + Arrays.toString(val))
        );

        return Optional.of(result);
    }

    @Override
    protected Optional<Integer> getMatrixSizes() {
        int size = Math.toIntExact(Math.round(Math.random() * 17 + 3));

        log.info("Matrix size: " + size);

        return Optional.of(size);
    }
}
