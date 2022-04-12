package dto;

import lombok.Value;

import java.util.List;

@Value
public class Slice {
    double startPoint;
    double stopPoint;

    public Slice(List<Double> list) {
        startPoint = list.get(0);
        stopPoint = list.get(1);
    }

    public double getDist() {
        return stopPoint - startPoint;
    }
}
