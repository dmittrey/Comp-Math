package main;

import dto.Function;
import dto.Slice;
import dto.input.InputResult;
import input.Console;
import input.IOReader;
import lombok.extern.java.Log;
import utility.Correctness;
import utility.outputformatting.FormattingWriter;
import utility.resolvemethods.ResolveMethod;
import utility.resolvemethods.TrapezoidMethod;
import utility.storage.Functions;

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

        }
    }

    private static boolean checkInputResultsForAvailable(Correctness... inputResult) {
        for (Correctness result : inputResult) {
            if (result.isIncorrect()) return false;
        }

        return true;
    }
}
