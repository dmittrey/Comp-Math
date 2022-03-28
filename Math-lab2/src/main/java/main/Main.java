package main;

import input.DataReader;
import input.InputSource;
import dto.Equation;
import utility.resolvers.SecantMethodResolver;
import utility.OutputFormatter;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            OutputFormatter outputFormatter = new OutputFormatter();

            InputSource inputSource = readInputSource(scanner);

            DataReader dataReader = inputSource.getConstructorFunction().create();
            dataReader.getRequiredData(scanner, outputFormatter);

            dataReader.readData().ifPresent(data -> {
                SecantMethodResolver secantMethodResolver = new SecantMethodResolver(data);

                outputFormatter.printResolveResult(secantMethodResolver.resolve(outputFormatter,
                        inputSource == InputSource.GENERATOR));
            });

            Optional<Equation> srcEquation = dataReader.readData();

            if (!srcEquation.isPresent()) {
                outputFormatter.printUnableToInputMatrix();
                return;
            }

            SecantMethodResolver secantMethodResolver = new SecantMethodResolver(srcMatrix.get());

            outputFormatter.printResolveResult(secantMethodResolver.resolve(outputFormatter,
                    inputSource == InputSource.GENERATOR));
        }
    }

    private static InputSource readInputSource(Scanner scanner) {
        while (true) {
            System.out.print("Enter source of matrix input");

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
