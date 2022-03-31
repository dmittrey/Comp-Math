package utility.resolvers.system;

import dto.*;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Метод простых итераций
 */
@Log
public class FixedPointIterationMethodResolver implements Function<EquationSystem, EquationSystemRoot> {

    /**
     * 1) Применяем к каждому уравнению:
     * 1.1) Прибавим x к обеим частям уравнения x=x+L*f(x)
     * 1.2) Найти производную от правой части
     * 1.3) Найти искомый коэффициент
     * 1.4) В полученную справа часть постоянно подставлять полученный x
     * 2) Делать так пока не дойдем до достаточного условия сходимости
     */
    @Override
    public EquationSystemRoot apply(EquationSystem equationSystem) {

        List<Double> roots;
        List<Double> newRoots = getInitialXList(equationSystem);
        List<Function<List<Double>, Double>> mappingWithoutXFunctions = equationSystem.getMappingWithoutXFunctions();
        int counter = 0;

        do {
            counter++;
            roots = newRoots;
            newRoots = new ArrayList<>();
            for (int i = 0; i < roots.size(); i++) {
                newRoots.add(mappingWithoutXFunctions.get(i).apply(roots));
            }
        } while (isContinue(roots, newRoots, equationSystem.getEpsilon()));

        List<EquationRoot> equationRoots = new ArrayList<>();
        for (int i = 0; i < newRoots.size(); i++) {
            equationRoots.add(new EquationRoot(newRoots.get(i),
                    equationSystem.getEquationList().get(i).getMappingFunction().apply(newRoots),
                    counter));
        }
        return new EquationSystemRoot(equationRoots);
    }

    private List<Double> getInitialXList(EquationSystem equationSystem) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < equationSystem.getEquationList().size(); i++) {
            result.add(equationSystem.getSlice().getMedian());
        }
        return result;
    }

    private boolean isContinue(List<Double> previousRoots, List<Double> roots, double epsilon) {
        for (int i = 0; i < previousRoots.size(); i++) {
            if (Math.abs(previousRoots.get(i) - roots.get(i)) > epsilon) return true;
        }
        return false;
    }
}