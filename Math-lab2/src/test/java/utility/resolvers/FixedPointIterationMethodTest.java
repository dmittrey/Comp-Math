package utility.resolvers;

import dto.Equation;
import dto.EquationRoot;
import dto.Slice;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.equations.EquationTypes;

import java.util.List;

/**
 * Метод простых итераций
 */
@Log
class FixedPointIterationMethodTest {

    Equation testeeEquation = new Equation();
    FixedPointIterationMethodResolver fixedPointIterationMethodResolver = new FixedPointIterationMethodResolver();
    SecantMethodResolver secantMethodResolver = new SecantMethodResolver();

    @Test
    void testFixedPointIterationMethod() {
        fillEquation1();

        EquationRoot root = fixedPointIterationMethodResolver.apply(testeeEquation);

        log.info("Root is: " + root);
    }

    @Test
    void testSecantMethod() {
        fillEquation1();

        EquationRoot root = secantMethodResolver.apply(testeeEquation);

        log.info("Root is: " + root);
    }

    @Test
    void testMappingFunction() {
        fillEquation1();

        log.info(String.valueOf(testeeEquation.getMappingFunction().apply(-1.5)));
        log.info(String.valueOf(testeeEquation.getMappingWithoutXFunction().apply(-2.)));
    }

    private void fillEquation1() {
        log.info("Setting equation 1");
        testeeEquation.setSlice(new Slice(-2, -1));
        testeeEquation.setEpsilon(0.01);
        testeeEquation = EquationTypes.FIRST_EQUATION.getEquationInitFunction().apply(testeeEquation);
    }
}