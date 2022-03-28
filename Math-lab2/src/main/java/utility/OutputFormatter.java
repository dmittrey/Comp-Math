package utility;

import dto.EquationRoot;
import dto.ResolveResult;
import lombok.extern.java.Log;

@Log
public class OutputFormatter {

    public void printResolveResult(ResolveResult resolveResult) {
        switch (resolveResult) {
            case COVERAGE_CONDITION_FAILED -> System.out.println("The convergence condition is violated! :(");
            case SUCCESSFUL -> System.out.println("Successful! :)");
            default -> System.err.println("Unreached statement!");
        }
    }

    public void printAnswer(EquationRoot equationRoot) {
        System.out.println("Answer:");
        System.out.println("X: " + equationRoot.getX() +
                "Y: " + equationRoot.getY() +
                "Count: " + equationRoot.getCount());
    }

    public void write(String expression) {
        System.out.print(expression);
    }
}
