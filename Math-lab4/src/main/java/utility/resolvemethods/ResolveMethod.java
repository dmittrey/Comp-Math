package utility.resolvemethods;

import dto.Point;

import java.util.List;
import java.util.Set;

@FunctionalInterface
public interface ResolveMethod {
    Double resolve(List<Point> interpolatingPoints);
}
