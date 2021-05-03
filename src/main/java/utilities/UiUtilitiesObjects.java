package utilities;

import base.Base;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import utilities.errors.*;
import utilities.javaScriptUtils.JavaScriptUtil;
import utilities.uiActions.UiActions;
import utilities.verfications.Verifications;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static utilities.errors.ErrorUtils.fail;

@Slf4j
public class UiUtilitiesObjects extends Base implements WrapperUiObjects {

    public UiActions uiActions() {
        return new UiActions();
    }
    public Verifications verifications() {
        return new Verifications();
    }
    public JavaScriptUtil jsUtil() {
        return new JavaScriptUtil();
    }
    public UiUtilitiesObjects uiUtilitiesObjects() {
        return new UiUtilitiesObjects();
    }

    @Override
    @Description("wrapper")
    public void wrapper(Consumer<UiUtilitiesObjects> consumer, boolean fail) {
        wrapper(consumer, WebDriverException.class, fail);
    }

    @Override
    @Description("wrapper")
    public <A,B extends Exception> Consumer<A> wrapper(
            Consumer<A> consumer, Class <B> clazz, boolean fail) {
        return action -> {
            try {
                consumer.accept(action);
            } catch (Exception exception) {
                log.info(exception.getMessage());
                try {
                    B cast = clazz.cast(exception);
                    log.info(cast.getMessage() +  error);
                } catch (Exception exception1) {
                    if (fail) {
                        fail(ProjectsErrors.class, error, exception1,
                                Reasons.GENERAL, Category.INTERNAL, Severity.HIGH);
                    }
                }
            }
        };
    }

    @Override
    @Description("wrapper")
    public <A,B,E extends Exception> BiConsumer<A,B> wrapper(
            BiConsumer<A,B> consumer, Class <E> clazz, boolean fail) {
        return (action1, action2) -> {
            try {
                consumer.accept(action1,action2);
            } catch (Exception exception) {
                log.info(exception.getMessage() +  error);
                try {
                    E cast = clazz.cast(exception);
                    log.info(cast.getMessage());
                } catch (Exception exception1) {
                    if (fail) {
                        fail(ProjectsErrors.class, error, exception1,
                                Reasons.GENERAL, Category.INTERNAL, Severity.HIGH);
                    }
                }
            }
        };
    }

    private final String error = "error from ui actions : ";
}
