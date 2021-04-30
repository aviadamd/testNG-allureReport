package utilities;

import base.Base;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import utilities.javaScriptUtils.JavaScriptUtil;
import utilities.uiActions.UiActions;
import utilities.verfications.Verifications;

import java.util.function.Consumer;

@Slf4j
public class Utilities extends Base {

    @Description("ui actions wrapper")
    public Consumer<Triple<UiActions,Verifications,JavaScriptUtil>>
    uiActionsWrapper(Consumer<Triple<UiActions,Verifications,JavaScriptUtil>> uiActionsConsumer, boolean fail) {
        return action -> {
            try {
                uiActionsConsumer.accept(Triple.of(new UiActions(),new Verifications(),new JavaScriptUtil()));
            } catch (WebDriverException e) {
                String error = "error from ui actions : ";
                log.info(error + e.getMessage());
                if (fail) Assert.fail(error + e.getMessage());
            }
        };
    }
}
