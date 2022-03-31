package input;

import lombok.Getter;

@Getter
public enum InputSource {
    CONSOLE("Console", UserIO::new),
    GENERATOR("Generator", MatrixGenerator::new),
    FILE("File", FileReader::new);

    private final String description;
    private final ObjectConstructorFunction<DataReader> dataReaderInitFunction;

    InputSource(String aDescription, ObjectConstructorFunction<DataReader> aDataReaderInitFunction) {
        description = aDescription;
        dataReaderInitFunction = aDataReaderInitFunction;
    }

    public static InputSource getConstant(String description) {
        return switch (description) {
            case "Console" -> CONSOLE;
            case "Generator" -> GENERATOR;
            case "File" -> FILE;
            default -> null;
        };
    }
}
