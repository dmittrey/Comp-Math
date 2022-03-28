package input;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatrixGenerator extends DataReader {


    @Override
    protected Optional<List<Double>> getEquationCoefficients() {

        getOutputFormatter().write("Enter count of values in equation: ");
        int countOfValues = getScanner().nextInt();

        ArrayList<Double> resultEquation = new ArrayList<>();
        for (int i = 0; i < countOfValues; i++) {
            resultEquation.add(Math.random() * 15);
        }
        return Optional.of(resultEquation);

    }
}
