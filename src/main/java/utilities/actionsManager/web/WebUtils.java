package utilities.actionsManager.web;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.actionsManager.ActionsManager;

import java.util.Optional;

@Slf4j
public class WebUtils extends ActionsManager {

    @Override
    public void click(WebElement element) {
        uiActions().elementToBeClickable(element).ifPresent(e -> {
            if (!(element.getText().isEmpty() || element.getText().isBlank())) {
                log.info("about to click on : " + element.getText());
            }
            log.info("about to click on : " + element);
            Optional.of(element).ifPresentOrElse(WebElement::click, Assert::fail);
        });
    }
}
