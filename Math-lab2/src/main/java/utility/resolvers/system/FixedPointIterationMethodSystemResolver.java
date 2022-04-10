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
public class FixedPointIterationMethodSystemResolver implements Function<EquationSystem, EquationSystemRoot> {

    @Override
    public EquationSystemRoot apply(EquationSystem equationSystem) {

        List<Double> roots;
        List<Double> newRoots = getInitialXList(equationSystem);
        List<Function<List<Double>, Double>> mappingFunctions = equationSystem.getMappingFunctions();
        int counter = 0;

        do {
            counter++;
            roots = newRoots;
            newRoots = new ArrayList<>();
            for (int i = 0; i < roots.size(); i++) {
                newRoots.add(roots.get(i) - 1./11.*mappingFunctions.get(i).apply(roots));
            }
        } while (isContinue(roots, newRoots, equationSystem.getEpsilon()));

        List<EquationRoot> equationRoots = new ArrayList<>();
        for (Double newRoot : newRoots) {
            equationRoots.add(new EquationRoot(newRoot,
                    counter));
        }
        return new EquationSystemRoot(equationRoots);
    }

    private List<Double> getInitialXList(EquationSystem equationSystem) {
        return equationSystem.getFirstVariables();
    }

    private boolean isContinue(List<Double> previousRoots, List<Double> roots, double epsilon) {
        for (int i = 0; i < previousRoots.size(); i++) {
            if (Math.abs(previousRoots.get(i) - roots.get(i)) > epsilon) return true;
        }
        return false;
    }
}