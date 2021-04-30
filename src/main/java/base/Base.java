package base;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxy;
import one.util.streamex.StreamEx;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import ru.yandex.qatools.ashot.Screenshot;
import utilities.UiUtilitiesObjects;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import static org.openqa.selenium.OutputType.BYTES;

@Slf4j
public class Base {

    public static WebDriver driver;
    public static Proxy seleniumProxy;
    public static BrowserMobProxy mobProxy;
    public static AppiumDriverLocalService server;
    public static PropertyConfig getProperty;
    public static UiUtilitiesObjects utilities;
    public static Screenshot imageScreenShot;

    @BeforeClass
    public void initClass() {
        String path = "/src/main/resources/config.properties";
        getProperty = new PropertyConfig(path);
        utilities = new UiUtilitiesObjects();
    }

    /**
     * take screen shot
     * @param driver pass driver as parameter to screen shot
     * @param testResult pass test result as parameter to screen shot
     */
    protected void takeScreenshot(final WebDriver driver, final ITestResult testResult) {
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(BYTES);
        final List<ScreenshotConsumer> screenshotConsumers = new CopyOnWriteArrayList<>();
        StreamEx.of(screenshotConsumers).forEach(sc -> sc.handle(screenshot, testResult));
    }
}
