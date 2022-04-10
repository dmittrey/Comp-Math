package utility.equations;

public enum EquationSystemsTypes {
    FIRST_SYSTEM("""
            x_1^2 + x2^2 - 1 = 0
            x_1^3 - x2 = 0
            """);


    private String description;

    EquationSystemsTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
