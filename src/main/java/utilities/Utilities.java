package utilities;

import base.Base;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Slf4j
public class Utilities extends Base {

    @Description("wrapper")
    public <A,B extends Exception> Consumer<A> wrapper(Consumer<A> consumer, Class <B> clazz, boolean fail) {
        return action -> {
          try {
              consumer.accept(action);
          } catch (Exception exception) {
              String error = "error from ui actions : ";
              try {
                  B cast = clazz.cast(exception);
                  log.info(cast.getMessage() +  error);
              } catch (Exception exception1) {
                  log.info(exception1.getMessage());
                  if (fail) Assert.fail(error + exception1.getMessage());
              }
          }
        };
    }

    @Description("wrapper")
    public <A,B,E extends Exception> BiConsumer<A,B> wrapper(BiConsumer<A,B> consumer, Class <E> clazz, boolean fail) {
        return (action1, action2) -> {
            try {
                consumer.accept(action1,action2);
            } catch (Exception exception) {
                String error = "error from ui actions : ";
                try {
                    E cast = clazz.cast(exception);
                    log.info(cast.getMessage() +  error);
                } catch (Exception exception1) {
                    log.info(exception1.getMessage());
                    if (fail) Assert.fail(error + exception1.getMessage());
                }
            }
        };
    }
}
