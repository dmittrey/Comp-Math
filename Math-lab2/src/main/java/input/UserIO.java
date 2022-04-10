package input;

import dto.ReadEquation;
import lombok.extern.java.Log;
import utility.equations.EquationTypes;

@Log
public class UserIO extends DataReader {
    @Override
    protected void getEquation(ReadEquation readEquation) {

        System.out.println("******************************");

        int count = 0;
        for (EquationTypes type : EquationTypes.values()) {
            System.out.println(count++ + " : " + type.getDescription());
        }

        System.out.print("Choose equation: ");

        while (true) {
            try {
                int chosenInt = getScanner().nextInt();
                if (chosenInt >= 0 && chosenInt < EquationTypes.values().length) {
                    EquationTypes.values()[chosenInt].getEquationInitFunction().apply(readEquation.getEquation());
                    break;
                }
            } catch (NumberFormatException ignored) {
            }
        }
    }
}
