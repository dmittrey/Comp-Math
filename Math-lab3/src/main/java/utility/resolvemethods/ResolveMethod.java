package utility.resolvemethods;

import dto.Function;
import utility.outputformatting.FirstGapNotifier;

import java.util.concurrent.ExecutionException;

@FunctionalInterface
public interface ResolveMethod {
    Double resolve(Function function, double epsilon, FirstGapNotifier notifyFunc) throws ExecutionException;
}
