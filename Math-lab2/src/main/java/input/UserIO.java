package input;

import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log
public class UserIO extends DataReader {
    @Override
    protected Optional<List<Double>> getEquationCoefficients() {

        getOutputFormatter().write("Write coefficients: ");

        List<Double> allMatches = new ArrayList<>();

        while (getScanner().hasNextLine()) {
            Matcher m = Pattern.compile("-?\\d+(\\.\\d+)?")
                    .matcher(getScanner().nextLine());
            while (m.find()) {
                allMatches.add(Double.parseDouble(m.group()));
            }

            if (!allMatches.isEmpty()) {
                Collections.reverse(allMatches);
                return Optional.of(allMatches);
            }
        }

        return Optional.empty();
    }
}
