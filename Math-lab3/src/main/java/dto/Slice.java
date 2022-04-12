package dto;

import lombok.Value;

@Value
public class Slice {
    double startPoint;
    double stopPoint;

    public double getDist() {
        return stopPoint - startPoint;
    }
}
