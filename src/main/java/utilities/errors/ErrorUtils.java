package utilities.errors;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ErrorUtils {

    public static <T extends ProjectsErrors> void fail(Class<T> cls, String message) {
        Class<?>[] clsArray = new Class[]{String.class};
        Object[] args = new Object[]{message};
        fail(cls, clsArray, args);
    }

    public static <T extends ProjectsErrors> void fail(Class<T> cls, String message, Reasons reason, Category category,
                                                       Severity severity) {
        Class<?>[] clsArray = new Class[]{String.class, Reasons.class, Category.class, Severity.class};
        Object[] args = new Object[]{message, reason, category, severity};
        fail(cls, clsArray, args);
    }

    public static <T extends ProjectsErrors> void fail(Class<T> cls, String message, Throwable cause) {
        Class<?>[] clsArray = new Class[]{String.class, Throwable.class};
        Object[] args = new Object[]{message, cause};
        fail(cls, clsArray, args);
    }

    public static <T extends ProjectsErrors> void fail(Class<T> cls, String message, Throwable cause, Reasons reason,
                                                       Category category, Severity severity) {
        Class<?>[] clsArray = new Class[]{String.class, Throwable.class, Reasons.class, Category.class, Severity.class};
        Object[] args = new Object[]{message, cause, reason, category, severity};
        fail(cls, clsArray, args);
    }

    public static List<String> handleError(String rootPackage, Throwable cause) {
        if (cause == null) {
            return Collections.emptyList();
        } else {
            Throwable throwable = cause;
            List<String> stack = new ArrayList<>();
            boolean firstEntry = true;
            stack.add(MessageFormat.format("Error occurred: ({0})", cause.getClass().getName()));
            String var5 = "\tat {0}: {1} ({2})";

            do {
                if (!firstEntry) {
                    stack.add(MessageFormat.format("Caused by: ({0})", throwable.getClass()));
                }

                stack.add(MessageFormat.format("Message: {0}", throwable.getMessage()));
                StackTraceElement[] var6 = cause.getStackTrace();
                int var7 = var6.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    StackTraceElement trace = var6[var8];
                    if (rootPackage == null || trace.getClassName().startsWith(rootPackage)) {
                        stack.add(MessageFormat.format("\tat {0}: {1} ({2})", trace.getClassName(),
                                trace.getMethodName(), trace.getLineNumber()));
                    }
                }

                firstEntry = false;
                throwable = throwable.getCause();
            } while(throwable != null);

            return stack;
        }
    }

    public static List<String> handleError(Throwable cause) {
        return handleError((String)null, cause);
    }

    private static <T extends ProjectsErrors> void fail(Class<T> cls, Class<?>[] clsArray, Object[] args) {
        try {
            Constructor<T> constructor = cls.getDeclaredConstructor(clsArray);
            throw (ProjectsErrors)constructor.newInstance(args);
        } catch (SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException | NoSuchMethodException var4) {
            throw new ProjectsErrors("Error occurred while throwing custom error.", var4.getCause());
        }
    }

    private ErrorUtils() {
    }
}
