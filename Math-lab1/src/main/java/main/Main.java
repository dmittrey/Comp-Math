package main;

import input.DataReader;
import input.InputSource;
import matrix.Matrix;
import utility.MatrixResolver;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            InputSource inputSource = readInputSource(scanner, "Enter source of matrix input");

            DataReader dataReader = inputSource.getConstructorFunction().create();

            dataReader.getRequiredData(scanner);
            Optional<Matrix> srcMatrix = dataReader.readData();

            if (!srcMatrix.isPresent()) {
                System.out.println("Unable to input matrix!");
                return;
            }

            MatrixResolver matrixResolver = new MatrixResolver(srcMatrix.get());
            Optional<Double[]> result = matrixResolver.resolve();
        }
    }

    private static InputSource readInputSource(Scanner scanner, String query) {

        while (true) {

            System.out.print(query);

            System.out.print("[");
            for (InputSource option : InputSource.values()) {
                System.out.printf(" %s ", option.getDescription());
            }
            System.out.print("]: ");

            InputSource source = InputSource.getConstant(scanner.nextLine());

            if (source != null) return source;
        }
    }
}
