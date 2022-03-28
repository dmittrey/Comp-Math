package main;

import dto.ReadEquation;
import input.DataReader;
import input.InputSource;
import utility.EquationResolver;
import utility.resolvers.FixedPointIterationMethodResolver;
import utility.resolvers.SecantMethodResolver;
import utility.OutputFormatter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            OutputFormatter outputFormatter = new OutputFormatter();

            InputSource inputSource = readInputSource(scanner);

            DataReader dataReader = inputSource.getConstructorFunction().create();

            System.out.println(dataReader.getClass().getName());

            dataReader.getRequiredData(scanner, outputFormatter);

            EquationResolver equationResolver = new EquationResolver();
            equationResolver.addResolveMethod(new SecantMethodResolver());
            equationResolver.addResolveMethod(new FixedPointIterationMethodResolver());

            ReadEquation newEquation = dataReader.readData();

            if (newEquation.isSuccessful()) {
                equationResolver.resolve(newEquation.getEquation()).forEach(System.out::println);
            } else {
                outputFormatter.printReadStatus(newEquation.getReadStatus());
            }
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
