package input;

import matrix.Matrix;

import java.util.Scanner;

//todo:dmittrey Выделить общие реализации в абстрактную сущность, сделать класс для
// какиех-то cross запросов UserIO and MatrixGenerator
public interface DataReader {

    Matrix readData();

    void getRequiredData(Scanner scanner);

    double getEpsilon();
}
