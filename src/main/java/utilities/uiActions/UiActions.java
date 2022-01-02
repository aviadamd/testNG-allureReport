package utilities.uiActions;

import base.FactoryDriverInit;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import utilities.WaitCondition;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Optional;

@Slf4j
public class UiActions extends FactoryDriverInit {

    @Description("element presented")
    public Optional<Boolean> elementPresented(WebElement element, int timeOut) {
        return Optional.of(webDriverWait(timeOut, ExpectedConditions.visibilityOf(element), element));
    }

    @Description("element to be clickable")
    public Optional<Boolean> elementToBeClickable(WebElement element) {
       return Optional.of(webDriverWait(10, ExpectedConditions.elementToBeClickable(element), element));
    }

    @Description("wait for")
    private WebElement waitFor(WebElement element, WaitCondition condition) {
        WebDriverWait driverWait = new WebDriverWait(getDriver(),5);
        return driverWait.until(condition.getType().apply(element));
    }

    @Description("web driver wait")
    public <T> boolean webDriverWait(int timeOut, ExpectedCondition<T> conditions, WebElement element) {
        try {
            new WebDriverWait(getDriver(), timeOut).until(conditions.compose(driver -> {
                log.info("condition :" + element.getText() + conditions);
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

    @Description("element screen shot")
    public void elementScreenShot(WebElement imageElement, String imageName) {
        try {
            imageScreenShot = new AShot()
                    .coordsProvider(new WebDriverCoordsProvider())
                    .takeScreenshot(driver, imageElement);
            ImageIO.write(imageScreenShot.getImage(),"png",
                    new File("./imageRepository/" + imageName));
        } catch (IOException ioException) {
            log.debug(ioException.getMessage());
        }
    }

    @Description("drag to")
    public <T extends TouchAction<T>> T dragTo(MobileElement from, MobileElement to, T actions) {
        Point source = from.getCenter();
        Point target = to.getCenter();
        return actions.press(PointOption.point(source.getX(), source.getY()))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(5)))
                .moveTo(PointOption.point(target.getX(), target.getY()))
                .release().waitAction(WaitOptions.waitOptions(Duration.ofMillis(5)));
    }
}
