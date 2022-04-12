package utility.resolvemethods;

import dto.Function;
import exceptions.SecondGapException;

import java.util.concurrent.*;

public class TrapezoidMethod implements ResolveMethod {

    private static final double EPSILON = Math.pow(10, -9);

    private final ExecutorService resolveService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    public Double resolve(Function function, double epsilon) throws ExecutionException {

        int countOfSlices = 5;

        while (true) {

            Future<Double> firstTask = resolveService.submit(
                    resolveByMethod(function, countOfSlices)
            );

            Future<Double> secondTask = resolveService.submit(
                    resolveByMethod(function, countOfSlices * 2)
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

    public Callable<Double> resolveByMethod(Function function, int countOfSlices) {
        return () -> {
            double stepDist = function.getSlice().getDist() / countOfSlices;

            double sumOfFunctionResults = 0;

            for (double currentPoint = function.getSlice().getStartPoint() + stepDist;
                 currentPoint <= function.getSlice().getStopPoint() - stepDist;
                 currentPoint += stepDist) {
                sumOfFunctionResults += bridgeGap(function, currentPoint);
            }

            return stepDist / 2 * (function.getOwnFunction().apply(function.getSlice().getStartPoint())
                    + function.getOwnFunction().apply(function.getSlice().getStopPoint())
                    + 2 * sumOfFunctionResults);
        };
    }

    public Double bridgeGap(Function function, double targetPoint) throws SecondGapException {
        if (function.getOwnFunction().apply(targetPoint) == Double.POSITIVE_INFINITY ||
                function.getOwnFunction().apply(targetPoint) == Double.NEGATIVE_INFINITY) throw new SecondGapException(targetPoint);

        return (function.getOwnFunction().apply(targetPoint - EPSILON) +
                function.getOwnFunction().apply(targetPoint + EPSILON)) / 2;
    }
}
