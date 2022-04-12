package utility.resolvemethods;

import dto.Function;

import java.util.concurrent.ExecutionException;

@FunctionalInterface
public interface ResolveMethod {
    Double resolve(Function function, double epsilon) throws ExecutionException;
}
