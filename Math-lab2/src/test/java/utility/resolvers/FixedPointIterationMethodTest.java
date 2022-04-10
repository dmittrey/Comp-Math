package utility.resolvers;

import dto.Equation;
import dto.EquationRoot;
import dto.EquationSystem;
import dto.Slice;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import utility.equations.EquationFactory;
import utility.equations.EquationTypes;
import utility.resolvers.system.FixedPointIterationMethodSystemResolver;

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
    void anotherTest() {
        EquationSystem equationSystem = EquationFactory.getSystem();
        equationSystem.setEpsilon(0.01);
        equationSystem.setSlice(new Slice(0., 1.));

        FixedPointIterationMethodSystemResolver fixedPointIterationMethodResolver = new FixedPointIterationMethodSystemResolver();

        log.info(fixedPointIterationMethodResolver.apply(equationSystem).getEquationRootList().toString());
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
        testeeEquation = EquationTypes.SECOND_EQUATION.getEquationInitFunction().apply(testeeEquation);
    }
}