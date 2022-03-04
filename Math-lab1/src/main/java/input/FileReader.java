package input;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import matrix.Matrix;
import utility.OutputFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

@Log
public class FileReader extends DataReader {

    @Getter
    @Setter
    File sourceFile;

    @Setter
    Scanner openedScanner;

    @Override
    public void getRequiredData(Scanner scanner, OutputFormatter outputFormatter) {

        super.getRequiredData(scanner, outputFormatter);

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
    public Optional<Matrix> readData() {
        try (Scanner fileScanner = new Scanner(new java.io.FileReader(sourceFile))) {

            setOpenedScanner(fileScanner);

            return super.readData();
        } catch (FileNotFoundException | NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Double> getEpsilon() {
        String newString = openedScanner.nextLine();
        return Optional.of(Double.parseDouble(newString));
    }

    @Override
    protected Optional<Double[][]> getMatrixValues(int matrixSize) {

        Double[][] newMatrix = new Double[matrixSize][matrixSize + 1];

        for (int i = 0; i < matrixSize; i++) {
            Optional<Double[]> newRow = getRow(openedScanner, matrixSize + 1);
            if (!newRow.isPresent()) return Optional.empty();

            newMatrix[i] = newRow.get();

            if (i == matrixSize - 1) return Optional.of(newMatrix);
        }

        return Optional.empty();
    }

    @Override
    protected Optional<Integer> getMatrixSizes() {
        String newString = openedScanner.nextLine();

        int sizeValue = Integer.parseInt(newString);

        if (sizeValue >= 3 && sizeValue <= 20) return Optional.of(sizeValue);

        return Optional.empty();
    }
}