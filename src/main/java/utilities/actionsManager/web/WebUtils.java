package utilities.actionsManager.web;

import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.actionsManager.ActionsManager;
import java.util.Optional;

@Slf4j
public class WebUtils extends ActionsManager {

    @Override
    public void click(WebElement element) {
        utilities.uiActions().elementToBeClickable(element).ifPresent(e -> {
            if (!(element.getText().isEmpty() || element.getText().isBlank())) {
                log.info("about to click on : " + element.getText());
            }
            log.info("about to click on : " + element);
            Optional.of(element).ifPresentOrElse(WebElement::click, Assert::fail);
        });
    }

    @Override
    @Description("click optional")
    public void clickOptional(WebElement element) {
        if (utilities.uiActions().elementToBeClickable(element).isPresent()
                || utilities.uiActions().elementPresented(element,1).isPresent()) {
            click(element);
        }
    }

    @Override
    @Description("clear")
    public void clear(WebElement element) {
        click(element);
        element.clear();
    }

    @Override
    @Description("send keys")
    public void sendKeys(WebElement element, String keys) {
        if (utilities.uiActions().elementToBeClickable(element).isPresent()) {
            element.sendKeys(keys);
        } else Assert.fail("fail send keys to " + element.toString());
    }
}
