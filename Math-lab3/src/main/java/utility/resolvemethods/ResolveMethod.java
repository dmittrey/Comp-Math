package utility.resolvemethods;

import dto.Function;
import exceptions.SecondGapException;

@FunctionalInterface
public interface ResolveMethod {
    Double resolve(Function function, double countOfSlices, double epsilon) throws SecondGapException;
}
