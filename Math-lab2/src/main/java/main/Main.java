package main;

import dto.EquationSystem;
import dto.ReadEquation;
import input.DataReader;
import input.InputSource;
import lombok.extern.java.Log;
import utility.equations.EquationFactory;
import utility.equations.EquationResolver;
import utility.equations.EquationSystemsTypes;
import utility.resolvers.FixedPointIterationMethodResolver;
import utility.resolvers.SecantMethodResolver;
import utility.OutputFormatter;
import utility.resolvers.system.FixedPointIterationMethodSystemResolver;

import java.util.Scanner;

@Log
public class Main {
    //todo Отчёт

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            OutputFormatter outputFormatter = new OutputFormatter();

            DataReader dataReader = InputSource.CONSOLE.getDataReaderInitFunction().create();

            dataReader.getRequiredData(scanner, outputFormatter);

            EquationResolver equationResolver = new EquationResolver();
            equationResolver.addResolveMethod(new SecantMethodResolver());
            equationResolver.addResolveMethod(new FixedPointIterationMethodResolver());

            ReadEquation newEquation = dataReader.readData();

            if (newEquation.isSuccessful()) {
                equationResolver.resolve(newEquation.getEquation());
            } else {
                outputFormatter.printReadStatus(newEquation.getReadStatus());
            }

            EquationSystem equationSystem = EquationFactory.getSystem();
            ReadEquation readEquation = dataReader.readWithoutEquationsData();
            equationSystem.setEpsilon(readEquation.getEquation().getEpsilon());
            equationSystem.setFirstVariables(readEquation.getVariables());

            System.out.println(EquationSystemsTypes.FIRST_SYSTEM.getDescription());

            FixedPointIterationMethodSystemResolver fixedPointIterationMethodSystemResolver = new FixedPointIterationMethodSystemResolver();
            fixedPointIterationMethodSystemResolver.apply(equationSystem)
                    .getEquationRootList()
                    .forEach(System.out::println);
        }
    }
}
