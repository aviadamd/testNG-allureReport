package utilities.actionsManager.web;

import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.UiUtilitiesObjects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class SharedWebUtils extends UiUtilitiesObjects {

    @Description("select by visible text")
    public void selectByVisibleText(WebElement element, String text) {
        Select value = new Select(element);
        value.selectByVisibleText(text);
    }

    @Description("select by value")
    public void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    @Description("mouse hover elements")
    public void mouseHoverElements(WebElement element1, WebElement element2) {
        uiActions().elementPresented(element1,5);
        Actions actions = new Actions(driver);
        actions.moveToElement(element1)
                .moveToElement(element2)
                .click()
                .build()
                .perform();
    }

    @Description("go to url")
    public void goToUrl(String url) {
        driver.manage().window().maximize();
        driver.get(getString(url).orElseThrow(IllegalArgumentException::new));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static Optional<String> getString(String input) {
        return Optional.ofNullable(input);
    }
}
