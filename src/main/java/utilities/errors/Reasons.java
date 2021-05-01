package utilities.errors;

public enum Reasons {
    GENERAL("General"),
    APP("Application"),
    APPIUM("Appium"),
    SELENIUM("Selenium"),
    CONFIG("Configurations"),
    SERVICE("Services");

    private final String name;
    private Reasons(String name) {
        this.name = name;
    }
    public String reason() {
        return this.name;
    }
}
