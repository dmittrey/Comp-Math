package exceptions;

import java.io.IOException;

public class SecondGapException extends IOException {
    public SecondGapException(double x) {
        super("Non-removable discontinuity found in point, where x = " + x + "!");
    }
}
