package dto;

import lombok.Value;

@Value
public class Slice {
    double start;
    double stop;

    public boolean isDifferentSignedEnds() {
        return (start < 0 && stop > 0) || (start > 0 && stop < 0);
    }

    public double getMedian() {
        return (start + stop) / 2;
    }
}
