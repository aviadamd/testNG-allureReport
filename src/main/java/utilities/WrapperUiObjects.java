package utilities;

import io.qameta.allure.Description;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface WrapperUiObjects {
    @Description("wrapper")
    <A,B extends Exception> Consumer<A> wrapper(Consumer<A> consumer, Class <B> clazz, boolean fail);

    @Description("wrapper")
    <A,B,E extends Exception> BiConsumer<A,B> wrapper(BiConsumer<A,B> consumer, Class <E> clazz, boolean fail);
}
