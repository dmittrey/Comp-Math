package dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleFunction;

@Data
public class Equation implements Cloneable {

    private Slice slice;
    private List<Double> equationCoefficients;
    private double epsilon;

    public void setEquationCoefficients(List<Double> equationCoefficients) {
        this.equationCoefficients = equationCoefficients;
    }

    public DoubleFunction<Double> getMappingFunction() {
        return xValue -> {
            double result = 0;
            for (int i = 0; i < equationCoefficients.size(); i++) {
                result += Math.pow(xValue, i) * equationCoefficients.get(i);
            }
            return result;
        };
    }

    public DoubleFunction<Double> getMappingWithoutXFunction() {
        return xValue -> {
            double result = 0;
            for (int i = 0; i < equationCoefficients.size(); i++) {
                result += Math.pow(xValue, i - 1) * equationCoefficients.get(i);
            }
            return result;
        };
    }

    @Override
    public Equation clone() throws CloneNotSupportedException {
        Equation clone = (Equation) super.clone();
        clone.setEquationCoefficients(new ArrayList<>());
        clone.setEpsilon(epsilon);

        Collections.copy(clone.getEquationCoefficients(), equationCoefficients);

        return clone;
    }
}
