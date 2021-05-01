package utilities.errors;

public enum Category {
    INTERNAL("Internal"),
    VALIDATION("Validation"),
    RETRIEVAL("Retrieval"),
    UI("UI"),
    MESSAGE("Message");

    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public String category() {
        return this.name;
    }
}
