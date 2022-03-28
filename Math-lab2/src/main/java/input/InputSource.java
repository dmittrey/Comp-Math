package input;

public enum InputSource {
    CONSOLE("Console", UserIO::new),
    GENERATOR("Generator", MatrixGenerator::new),
    FILE("File", FileReader::new);

    private final String description;
    private final DataReaderConstructorFunction dataReaderInitFunction;

    InputSource(String aDescription, DataReaderConstructorFunction aDataReaderInitFunction) {
        description = aDescription;
        dataReaderInitFunction = aDataReaderInitFunction;
    }

    public String getDescription() {
        return description;
    }

    public DataReaderConstructorFunction getConstructorFunction() {
        return dataReaderInitFunction;
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
