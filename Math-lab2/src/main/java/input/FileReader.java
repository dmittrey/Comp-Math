package input;

import dto.ReadEquation;
import lombok.Setter;
import utility.OutputFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Setter
public class FileReader extends DataReader {

    private Scanner openedScanner;

    @Override
    public void getRequiredData(Scanner scanner, OutputFormatter outputFormatter) {

        super.getRequiredData(scanner, outputFormatter);

        while (true) {
            outputFormatter.write("Enter file path: ");

            String answer = scanner.nextLine();
            File newFile = new File(answer);
            if (!newFile.exists()) outputFormatter.write("File doesn't exist!");
            else if (!newFile.isFile()) outputFormatter.write("It is not a file!");
            else if (!newFile.canRead()) outputFormatter.write("This file unable to reading!");
            else {
                try {
                    setOpenedScanner(new Scanner(newFile));
                } catch (FileNotFoundException ignored) {
                    System.err.println("Unreachable statement!");
                }
                break;
            }
        }
    }

    @Override
    protected Optional<List<Double>> getEquationCoefficients() {
        ArrayList<Double> result = new ArrayList<>();
        while (openedScanner.hasNextDouble()) {
            result.add(openedScanner.nextDouble());
        }
        Collections.reverse(result);

        return (result.isEmpty()) ? Optional.of(result) : Optional.empty();
    }

    @Override
    public ReadEquation readData() {
        return super.readData();
    }

    @Override
    public Optional<Double> getEpsilon() {
        return (openedScanner.hasNextDouble()) ? Optional.of(openedScanner.nextDouble()) : Optional.empty();
    }
}