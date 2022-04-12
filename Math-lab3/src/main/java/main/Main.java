package main;

import dto.Function;
import dto.Slice;
import dto.input.InputResult;
import input.Console;
import input.IOReader;
import lombok.extern.java.Log;
import utility.Correctness;
import utility.outputformatting.FormattingWriter;
import utility.storage.Functions;
import utility.resolvemethods.ResolveMethod;
import utility.resolvemethods.TrapezoidMethod;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Log
public class Main {

    public static void main(String[] args) {
        FormattingWriter formattingWriter = new FormattingWriter(System.out);

        try (Scanner scanner = new Scanner(System.in)) {

            Console console = new Console(scanner);
            IOReader ioReader = new IOReader(console, formattingWriter);

            ResolveMethod trapezoidMethod = new TrapezoidMethod();

            InputResult<Integer> functionNumber = ioReader.readFunctionChoice(Functions.values().length);
            InputResult<List<Double>> sliceRange = ioReader.readSlicePoints();
            InputResult<Double> epsilon = ioReader.readEpsilon();

            if (checkInputResultsForAvailable(functionNumber, sliceRange, epsilon)) {
                Function function = new Function(
                        Functions.values()[functionNumber.getReadValue() - 1],
                        new Slice(sliceRange.getReadValue())
                );

                formattingWriter.printAnswer(
                        trapezoidMethod.resolve(
                                function,
                                epsilon.getReadValue(),
                                formattingWriter
                        ).toString()
                );

            } else {
                formattingWriter.printWarning("Something went wrong!");
            }
        } catch (ExecutionException e) {
            formattingWriter.printRed("Non-removable discontinuity found in point, where x = 2.0!");
        }
    }

    private static boolean checkInputResultsForAvailable(Correctness... inputResult) {
        for (Correctness result : inputResult) {
            if (result.isIncorrect()) return false;
        }

        return true;
    }
}
