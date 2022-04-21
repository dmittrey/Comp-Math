package utility.resolvemethods;

import dto.Point;

import java.util.Set;

@FunctionalInterface
public interface ResolveMethod {
    Double resolve(Set<Point> interpolatingPoints);
}
