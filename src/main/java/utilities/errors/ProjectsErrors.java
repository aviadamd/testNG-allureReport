package utilities.errors;

import java.text.MessageFormat;

public class ProjectsErrors extends AssertionError {

    private final Category category;
    private final Reasons reasons;
    private final Severity severity;

    public ProjectsErrors(String message, Throwable cause, Reasons reason, Category category, Severity severity) {
        super(message, cause);
        this.reasons = reason;
        this.category = category;
        this.severity = severity;
    }

    public ProjectsErrors(String message) {
        this(message, null);
    }

    public ProjectsErrors(String message, Throwable cause) {
        this(message, cause, Reasons.GENERAL, Category.MESSAGE, Severity.CRITICAL);
    }

    public ProjectsErrors(String message, Reasons reason, Category category, Severity severity) {
        this(message, null, reason, category, severity);
    }

    public Category getCategory() {
        return this.category;
    }
    public Reasons getReason() {
        return this.reasons;
    }
    public Severity getSeverity() {
        return this.severity;
    }

    public String toString() {
        return MessageFormat.format("Error occurred: {0}\nReason: {1}\nCategory: {2}\nSeverity: {3}",
                this.getMessage(), this.reasons.reason(), this.category.category(), this.severity.name());
    }
}
