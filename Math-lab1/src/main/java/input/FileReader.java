package input;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

@Log
public class FileReader extends DataReader {

    @Getter
    @Setter
    File sourceFile;

    @Override
    public void getRequiredData(Scanner scanner) {
        while (true) {
            System.out.print("Enter file path: ");

            String answer = scanner.nextLine();
            java.io.File newFile = new File(answer);
            if (!newFile.exists()) System.out.println("File doesn't exist!");
            else if (!newFile.isFile()) System.out.println("It is not a file!");
            else if (!newFile.canRead()) System.out.println("This file unable to reading!");
            else {
                setSourceFile(newFile);
                break;
            }
        }
    }

    @Override
    public Optional<Double> getEpsilon() {
        try (Scanner fileScanner = new Scanner(new java.io.FileReader(sourceFile))) {
            if (fileScanner.hasNextDouble()) return Optional.of(fileScanner.nextDouble());
            else return Optional.empty();
        } catch (FileNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    protected Optional<Double[][]> getMatrixValues(int matrixSize) {

        try (Scanner fileScanner = new Scanner(new java.io.FileReader(sourceFile))) {

            Double[][] newMatrix = new Double[matrixSize][matrixSize + 1];

            for (int i = 0; i < matrixSize; i++) {
                Optional<Double[]> newRow = getRow(fileScanner, matrixSize + 1);
                if (!newRow.isPresent()) return Optional.empty();

                newMatrix[i] = newRow.get();

                if (i == matrixSize - 1) return Optional.of(newMatrix);
            }
        } catch (FileNotFoundException e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

    @Override
    protected Optional<Integer> getMatrixSizes() {
        try (Scanner fileScanner = new Scanner(new java.io.FileReader(sourceFile))) {
            if (fileScanner.hasNextInt()) {
                int newInt = fileScanner.nextInt();
                if (newInt >= 3 && newInt <= 20) return Optional.of(fileScanner.nextInt());
            }
            return Optional.empty();
        } catch (FileNotFoundException e) {
            return Optional.empty();
        }
    }
}