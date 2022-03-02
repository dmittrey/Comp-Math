package main;

import input.DataReader;
import input.InputSource;
import matrix.Matrix;
import utility.MatrixResolver;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        try (Scanner scanner = new Scanner(System.in)) {

            InputSource inputSource = readInputSource(scanner, "Enter source of matrix input");

            DataReader dataReader = inputSource.getConstructorFunction().create();

            dataReader.getRequiredData(scanner);
            Matrix srcMatrix = dataReader.readData();

            //ТОчка для расширения
            MatrixResolver matrixResolver = new MatrixResolver(srcMatrix);
            double[] result = matrixResolver.resolve();

            //Спросить что чел хочет ввести
//            if (readAnswer(scanner, printWriter, "Хотите сами ввести матрицу?(y/n)", "y", "n")) {
//                dataReader = new MatrixGenerator();
//            } else {
//                if (readAnswer(scanner, printWriter, "Укажите источник ввода(ф(айл)/р(учками))", "ф", "р")) {
//                    java.io.File file = getFilePath(scanner, printWriter, "Введите путь к файлу:");
//                    dataReader = new FileReader(file);
//                } else {
//                    dataReader = new UserIO(System.in);
//                }
//            }
        }

//        Matrix matrix = new Matrix();
//        MatrixResolver resolver = new MatrixResolver();
//
//        matrix.setMatrixValues(dataReader.readData());
//
//        resolver.setMatrix(matrix);
//
//        double[] result = resolver.resolve();


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
