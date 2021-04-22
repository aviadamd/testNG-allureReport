package utilities.uiActions;

import base.Base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import utilities.UiUtilitiesObjects;
import utilities.verfications.Verifications;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Slf4j
public class UiActions extends Base {

    public void perform(String text, BiConsumer<UiActions, Verifications> actionsConsumer) {
        log.info("{} action [{}]", text, "");
        actionsConsumer.accept(utilities.uiActions(), utilities.verifications());
    }


    public void perform(String text, Consumer<UiUtilitiesObjects> actionsConsumer) {
        log.info("{} action [{}]", text, "");
        actionsConsumer.accept(utilities.uiUtilitiesObjects());
    }

    public void click(WebElement element) {
        if (elementToBeClickable(element)) {
            element.click();
        } else Assert.fail("fail click on " + element.toString());
    }

    public void clickOptional(WebElement element) {
        if (elementToBeClickable(element) || elementPresented(element,1)) {
            click(element);
        }
    }

    public boolean elementPresented(WebElement element, int timeOut) {
        return webDriverWait(timeOut, ExpectedConditions.visibilityOf(element), element);
    }

    public boolean elementToBeClickable(WebElement element) {
       return webDriverWait(10, ExpectedConditions.elementToBeClickable(element), element);
    }

    public void sendKeys(WebElement element, String text) {
        if (elementToBeClickable(element)) {
            element.sendKeys(text);
        } else Assert.fail("fail send keys to " + element.toString());
    }

    public void selectByVisibleText(WebElement element, String text) {
        Select value = new Select(element);
        value.selectByVisibleText(text);
    }

    public void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void mouseHoverElements(WebElement element1, WebElement element2) {
        elementPresented(element1,5);
        Actions actions = new Actions(driver);
        actions.moveToElement(element1)
                .moveToElement(element2)
                .click()
                .build()
                .perform();
    }

    public <T> boolean webDriverWait(int timeOut, ExpectedCondition<T> conditions, WebElement element) {
        try {
            new WebDriverWait(driver, timeOut).until(conditions.compose(driver -> {
                log.info("condition :" + element.getText() +
                        conditions + " is true");
                return null;
            }));
            return true;
        } catch (TimeoutException | NullPointerException e) {
            log.error(conditions.toString() + " is false " + e.getMessage());
        } catch (WebDriverException w) {
            log.error(conditions.toString() + " is false " + w.getAdditionalInformation());
        }
        return false;
    }


    public void clear(WebElement element) {
        click(element);
        element.clear();
    }

    public void elementScreenShot(WebElement imageElement, String imageName) {
        try {
            imageScreenShot = new AShot()
                    .coordsProvider(new WebDriverCoordsProvider())
                    .takeScreenshot(driver,imageElement);
            ImageIO.write(imageScreenShot.getImage(),"png",
                    new File("./imageRepository/" + imageName));
        } catch (IOException ioException) {
            log.debug(ioException.getMessage());
        }
    }

    public <T extends TouchAction<T>> T dragTo(MobileElement from, MobileElement to, T actions) {
        Point source = from.getCenter();
        Point target = to.getCenter();
        return actions.press(PointOption.point(source.getX(), source.getY()))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(5)))
                .moveTo(PointOption.point(target.getX(), target.getY()))
                .release()
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(5)));
    }

    public static String cleanElementString(String element) {

        return element.replaceAll("[.*?]","");
    }
}
