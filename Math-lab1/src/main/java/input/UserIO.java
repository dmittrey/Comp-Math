package input;

import java.util.Optional;

public class UserIO extends DataReader {

    @Override
    protected Optional<Double[][]> getMatrixValues(int matrixSize) {
        Double[][] newMatrix = new Double[matrixSize][matrixSize + 1];
        while (true) {
            for (int i = 0; i < matrixSize; i++) {
                System.out.print("\tInput string № " + (i + 1) + " with free elements: \n\t");

                Optional<Double[]> newRow = getRow(getScanner(), matrixSize + 1);
                if (!newRow.isPresent()) break;

                newMatrix[i] = newRow.get();

                if (i == matrixSize - 1) {
                    return Optional.of(newMatrix);
                }
            }
            System.out.println("\tInput is incorrect! Try again: ");
        }
    }
}
