package input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class UserIO extends DataReader {
    @Override
    protected Optional<Double[]> getEquationCoefficients() {

        ArrayList<Double> result = new ArrayList<>();
        while (getScanner().hasNextDouble()) {
            result.add(getScanner().nextDouble());
        }
        Collections.reverse(result);

        return (result.isEmpty()) ? Optional.of(result.toArray(new Double[0])) : Optional.empty();
    }
}
