package dto;

import lombok.Data;

@Data
public class Equation implements Cloneable {

    private Double[] equationCoefficients;
    private int countOfCoefficients;
    private double epsilon;

    public void setEquationCoefficients(Double[] equationCoefficients) {
        this.equationCoefficients = equationCoefficients;
        this.countOfCoefficients = equationCoefficients.length;
    }

    @Override
    public Equation clone() throws CloneNotSupportedException {
        Equation clone = (Equation) super.clone();
        Equation newEquation = new Equation();
        newEquation.setEquationCoefficients(new Double[countOfCoefficients]);
        newEquation.setCountOfCoefficients(countOfCoefficients);
        newEquation.setEpsilon(epsilon);

        System.arraycopy(equationCoefficients, 0, newEquation.equationCoefficients, 0, countOfCoefficients);

        return newEquation;
    }
}
