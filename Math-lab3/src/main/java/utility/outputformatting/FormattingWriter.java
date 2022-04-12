package utility.outputformatting;

import lombok.RequiredArgsConstructor;
import utility.storage.Functions;

import java.io.PrintStream;

@RequiredArgsConstructor
public class FormattingWriter {

    private final PrintStream writer;

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void printFunctionsForChoice() {
        int counter = 1;

        for (Functions function : Functions.values()) {
            writer.println(counter++ + " - \n" + function.toString() + "\n");
        }

        writer.println("Enter function's number: ");
    }

    public void printSliceReadRequest() {
        writer.println("Enter slice start and end: ");
    }

    public void printEpsilonReadRequest() {
        writer.println("Enter epsilon: ");
    }

    public void printWarning(String message) {
        writer.println(ANSI_YELLOW + message + ANSI_RESET);
    }

    public void printAnswer(String message) {
        writer.println("Answer: " + ANSI_GREEN + message + ANSI_RESET);
    }
}
