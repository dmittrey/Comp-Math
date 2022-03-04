package input;

import lombok.extern.java.Log;

import java.util.Optional;

@Log
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

    @Override
    protected Optional<Integer> getMatrixSizes() {
        System.out.print("Input matrix size: ");
        while (true) {
            Optional<Integer> newInt = getIntNumber();
            if ((newInt.isPresent()) && (newInt.get() >= 3) && (newInt.get() <= 20)) return newInt;
        }
    }

    private Optional<Integer> getIntNumber() {
        String answer = super.getScanner().nextLine();
        try {
            return Optional.of(Integer.parseInt(answer));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }
}
