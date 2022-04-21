package utility.resolvemethods;

import dto.Function;
import exceptions.SecondGapException;
import lombok.extern.java.Log;
import utility.outputformatting.FirstGapNotifier;

import java.util.concurrent.*;

@Log
public class TrapezoidMethod implements ResolveMethod {

    private static final double EPSILON = Math.pow(10, -9);

    private final ExecutorService resolveService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    public Double resolve(Function function, double epsilon, FirstGapNotifier notifyFunc) throws ExecutionException {

        int countOfSlices = 5;

        while (true) {

            Future<Double> firstTask = resolveService.submit(
                    resolveByMethod(function, countOfSlices, notifyFunc)
            );

            Future<Double> secondTask = resolveService.submit(
                    resolveByMethod(function, countOfSlices * 2, notifyFunc)
            );

            try {
                if (Math.abs(secondTask.get() - firstTask.get()) < epsilon) return secondTask.get();
                else countOfSlices = countOfSlices * 4;
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    public Callable<Double> resolveByMethod(Function function, int countOfSlices, FirstGapNotifier notifyFunc) {
        return () -> {
            double stepDist = function.getSlice().getDist() / countOfSlices;

            double sumOfFunctionResults = 0;

            for (double currentPoint = function.getSlice().getStartPoint() + stepDist;
                 currentPoint <= function.getSlice().getStopPoint() - stepDist;
                 currentPoint += stepDist) {
                sumOfFunctionResults += bridgeGap(function, currentPoint, notifyFunc);
            }

            return stepDist / 2
                    * (function.getOwnFunction().apply(bridgeGap(function, function.getSlice().getStartPoint(), notifyFunc))
                    + function.getOwnFunction().apply(bridgeGap(function, function.getSlice().getStopPoint(), notifyFunc))
                    + 2 * sumOfFunctionResults);
        };
    }

    public Double bridgeGap(Function function, double targetPoint, FirstGapNotifier notifyFunc) throws SecondGapException {
        if (function.getOwnFunction().apply(targetPoint) == Double.POSITIVE_INFINITY ||
                function.getOwnFunction().apply(targetPoint) == Double.NEGATIVE_INFINITY)
            throw new SecondGapException(targetPoint);
        else if (Double.isNaN(function.getOwnFunction().apply(targetPoint))){
            notifyFunc.notifyGap(targetPoint);
            return (function.getOwnFunction().apply(targetPoint - EPSILON) +
                    function.getOwnFunction().apply(targetPoint + EPSILON)) / 2;
        }
        else {
            return (function.getOwnFunction().apply(targetPoint - EPSILON) +
                    function.getOwnFunction().apply(targetPoint + EPSILON)) / 2;
        }
    }
}
