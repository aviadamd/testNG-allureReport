package utilities.uiActions;

import base.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import utilities.WaitCondition;
import utilities.errors.ProjectsErrors;
import utilities.errors.Reasons;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import static utilities.WaitCondition.enabled;
import static utilities.WaitCondition.visible;

@Slf4j
public class UiActions extends Base {

    public static Optional<String> getString(String input) {
        return Optional.ofNullable(input);
    }

    @Description("go to url")
    public void goToUrl(String url) {
        driver.manage().window().maximize();
        driver.get(getString(url).orElseThrow(IllegalArgumentException::new));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Description("click")
    public void click(WebElement element) {
        if (elementToBeClickable(element)) {
            element.click();
        } else Assert.fail("fail click on " + element.toString());
    }

    @Description("click optional")
    public void clickOptional(WebElement element) {
        if (elementToBeClickable(element) || elementPresented(element,1)) {
            click(element);
        }
    }

    @Description("element presented")
    public boolean elementPresented(WebElement element, int timeOut) {
        return webDriverWait(timeOut, ExpectedConditions.visibilityOf(element), element);
    }

    @Description("element to be clickable")
    public boolean elementToBeClickable(WebElement element) {
       return webDriverWait(10, ExpectedConditions.elementToBeClickable(element), element);
    }

    @Description("send keys")
    public void sendKeys(WebElement element, String text) {
        if (elementToBeClickable(element)) {
            element.sendKeys(text);
        } else Assert.fail("fail send keys to " + element.toString());
    }

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
        elementPresented(element1,5);
        Actions actions = new Actions(driver);
        actions.moveToElement(element1)
                .moveToElement(element2)
                .click()
                .build()
                .perform();
    }

    @Description("click element")
    public void clickElement(WebElement element) {
        waitFor(element, enabled).click();
    }

    @Description("type")
    public void type(WebElement element, CharSequence text) {
        waitFor(element, visible).sendKeys(text);
    }

    @Description("wait for")
    private WebElement waitFor(WebElement element, WaitCondition condition) {
        WebDriverWait driverWait = new WebDriverWait(driver,5);
        return driverWait.until(condition.getType().apply(element));
    }

    @Description("web driver wait")
    public <T> boolean webDriverWait(int timeOut, ExpectedCondition<T> conditions, WebElement element) {
        try {
            new WebDriverWait(driver, timeOut).until(conditions.compose(driver -> {
                log.info("condition :" + element.getText() + conditions.toString());
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

    @Description("web driver wait")
    public boolean webDriverWait(int timeOut, WaitCondition conditions, WebElement element) {
        try {
            new WebDriverWait(driver, timeOut).until(conditions.getType().compose(driver -> {
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

    @Description("clear")
    public void clear(WebElement element) {
        click(element);
        element.clear();
    }

    @Description("element screen shot")
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

    @Description("drag to")
    public <T extends TouchAction<T>> T dragTo(MobileElement from, MobileElement to, T actions) {
        Point source = from.getCenter();
        Point target = to.getCenter();
        return actions.press(PointOption.point(source.getX(), source.getY()))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(5)))
                .moveTo(PointOption.point(target.getX(), target.getY()))
                .release().waitAction(WaitOptions.waitOptions(Duration.ofMillis(5)));
    }

    @Description("clean element string")
    public static String cleanElementString(String element) {
        return element.replaceAll("[.*?]","");
    }

    @Description("ui actions wrapper")
    public Consumer<UiActions> uiActionsWrapper(Consumer<UiActions> uiActionsConsumer, boolean fail) {
        return action -> {
            try {
                uiActionsConsumer.accept(new UiActions());
            } catch (WebDriverException e) {
                String error = "error from ui actions : ";
                log.info(error + e.getMessage());
                if (fail) Assert.fail(error + e.getMessage());
            }
        };
    }
}
