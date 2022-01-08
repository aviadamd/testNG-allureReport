package utilities;

import io.qameta.allure.Description;
import utilities.errors.Category;
import utilities.errors.Reasons;
import utilities.errors.Severity;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface WrapperObjects {

    @Description("wrapper")
    <A,B extends Exception> Consumer<A> wrapper(
            Consumer<A> consumer, Class <B> clazz, boolean fail);

    @Description("wrapper")
    <A,B,E extends Exception> BiConsumer<A,B> wrapper(
            BiConsumer<A,B> consumer, Class <E> clazz, boolean fail);

    @Description("wrapper")
    <A,B,E extends Exception> BiConsumer<A,B> wrapper(
            BiConsumer<A,B> consumer, Class <E> clazz, boolean fail,
            Reasons reasons, Category category, Severity severity
    );
}
