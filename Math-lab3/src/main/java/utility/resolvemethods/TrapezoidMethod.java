package utility.resolvemethods;

import dto.Function;

public class TrapezoidMethod implements ResolveMethod {

    @Override
    public Double resolve(Function function, double countOfSlices, double epsilon) {

        double stepDist = function.getSlice().getDist() / countOfSlices;

        double sumOfFunctionResults = 0;

        for (double currentPoint = function.getSlice().getStartPoint() + stepDist; currentPoint <= function.getSlice().getStopPoint() - stepDist; currentPoint += stepDist)
            sumOfFunctionResults += function.getOwnFunction().apply(currentPoint);

        return stepDist/2 * (function.getOwnFunction().apply(function.getSlice().getStartPoint())
                + function.getOwnFunction().apply(function.getSlice().getStopPoint())
                + 2 * sumOfFunctionResults);
    }
}
