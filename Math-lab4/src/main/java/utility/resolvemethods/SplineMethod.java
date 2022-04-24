package utility.resolvemethods;

import dto.Point;
import dto.SplineFunction;
import dto.SplineFunctionWithoutX;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Log
public class SplineMethod implements ResolveMethod {

    @Override
    public Double resolve(List<Point> interpolatingPoints) {
        List<SplineFunction> splineFunctions = new ArrayList<>();
        List<SplineFunctionWithoutX> splineFunctionWithoutX = new ArrayList<>();

        /* Для каждого участка составлено уравнение */
        for (int i = 0; i < interpolatingPoints.size() - 1; i++) {
            splineFunctions.add(
                    SplineFunction.getStartSplineFunction(
                            interpolatingPoints.get(i).getXCoordinate(),
                            interpolatingPoints.get(i + 1).getXCoordinate()
                    )
            );
        }


    }

    /* Чтобы найти все полученные коэффициенты выполним несколько условий: */

    /* 1) Сплайны должны проходить через узловые точки */
    private void getSplinesCrossNodalPoints(List<SplineFunction> splineFunctions,
                                            List<SplineFunctionWithoutX> splineFunctionWithoutX,
                                            List<Point> interpolatingPoints) {

        for (int i = 0; i < interpolatingPoints.size() - 1; i++) {
            splineFunctionWithoutX.add(
                    SplineFunctionWithoutX.insertX(
                            splineFunctions.get(i),
                            interpolatingPoints.get(i).getXCoordinate()
                    )
            );

            splineFunctionWithoutX.add(
                    SplineFunctionWithoutX.insertX(
                            splineFunctions.get(i),
                            interpolatingPoints.get(i + 1).getXCoordinate()
                    )
            );
        }
    }

    /*
    2) В стыках сплайнов должна обеспечиваться гладкость

    Для каждой точки первая и вторая производная стыков должны быть равны(но не для крайних точек)

    //todo Для двух взять первую производную и приравнять
    //todo Для двух взять вторую производную и приравнять




    //todo Сделать другую структуру данных
    */
    private void getSmooth(List<SplineFunction> splineFunctions,
                           List<SplineFunctionWithoutX> splineFunctionWithoutX,
                           List<Point> interpolatingPoints) {

        for (int i = 1; i < interpolatingPoints.size() - 1; i++) {
            splineFunctionWithoutX.add(
                    SplineFunctionWithoutX.insertX(
                            splineFunctions.get(i),
                            interpolatingPoints.get(i).getXCoordinate()
                    )
            );
        }

        for (int i = 1; i < interpolatingPoints.size() - 1; i++) {
            splineFunctionWithoutX.add(
                    SplineFunctionWithoutX.insertX(
                            splineFunctions.get(i),
                            interpolatingPoints.get(i).getXCoordinate()
                    )
            );
        }
        //Для всех неконечных точек приравниваем вторую и третью производную
    }
    //todo Захардкодить производные в общем виде
    //todo Подставить точки

}
