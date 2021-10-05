package utilities.actionsManager.mobile;

import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.actionsManager.ActionsManager;
import static org.testng.Assert.fail;

@Slf4j
public class MobileUtils extends ActionsManager {

    @Override
    public void click(WebElement element) {
        utilities.uiActions().elementToBeClickable(element).ifPresentOrElse(e -> {
            String text = element.getText() == null ? element.toString() : element.getText();
            log.info("about to click on: " + text);
            element.click();
        }, Assert::fail);
    }

    @Override
    @Description("click optional")
    public void clickOptional(WebElement element) {
        if (utilities.uiActions().elementToBeClickable(element).isPresent()
                || utilities.uiActions().elementPresented(element,1).isPresent()) {
            String text = element.getText() == null ? element.toString() : element.getText();
            log.info("about to click on: " + text);
            element.click();
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
        } else fail("fail send keys to " + element.toString());
    }
}
