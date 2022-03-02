package input;

import lombok.Setter;
import matrix.Matrix;

import java.io.File;
import java.util.Scanner;

public class FileReader implements DataReader {

    @Setter
    private java.io.File sourceFile;

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
    public double getEpsilon() {
        return 0;
    }

    private int getMatrixSizes() {

    }

    private double[][] getGeneratedMatrixValues() {

    }
}
