package utility.resolvemethods;

import dto.Function;

@FunctionalInterface
public interface ResolveMethod {
    Double resolve(Function function, double countOfSlices, double epsilon);
}
