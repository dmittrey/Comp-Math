package utility;

import lombok.extern.java.Log;

@Log
public class OutputFormatter {

    public void printResolveResult(ResolveResult resolveResult) {
        switch (resolveResult) {
            case DEGENERATE_MATRIX:
                System.out.println("Det = 0! :(");
                break;
            case PREDOMINANCE_OF_DIAGONAL_ELEMENTS_CONDITION:
                System.out.println("Predominance of diagonal elements condition isn't correct! :(");
                break;
            case COVERAGE_CONDITION_FAILED:
                System.out.println("The convergence condition is violated! :(");
                break;
            case SUCCESSFUL:
                System.out.println("Successful! :)");
                break;
            default:
                log.info("Unreached statement!");
                break;
        }
    }

    public void printUnableToInputMatrix() {
        System.out.println("Unable to input matrix!");
    }

    public void printAnswer(double[] result, double[] epsilonArray, int iterationNumber) {
        System.out.println("Answer:");
        for (int i = 1; i <= result.length; i++) {
            System.out.println("x" + i + " = " + result[i - 1] + " : " + "epsilon" + " = " + epsilonArray[i - 1]);
        }
        System.out.println("Iteration: " + iterationNumber);
    }

    public void write(String expression) {
        System.out.print(expression);
    }

    public void printMatrixValues(Double[][] matrixValues) {
        for (int i = 0; i < matrixValues.length; i++) {
            System.out.print("String №" + i + ": ");
            for (int j = 0; j < matrixValues.length + 1; j++) {
                System.out.print(" " + matrixValues[i][j] + " ");
            }
            System.out.println();
        }
    }
}
