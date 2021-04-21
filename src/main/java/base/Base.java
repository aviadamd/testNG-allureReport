package base;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import utilities.UiUtilitiesObjects;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Base {

    public static WebDriver driver;
    public static Proxy seleniumProxy;
    public static BrowserMobProxy mobProxy;
    public static AppiumDriverLocalService server;
    public static PropertyConfig getProperty;
    public static UiUtilitiesObjects utilities;
    public static Screenshot imageScreenShot;
    public static ImageDiff imageDiff;
    public ImageDiffer imageDiffer = new ImageDiffer();

    @BeforeClass
    public void initClass() {
        String path = "/src/main/resources/config.properties";
        getProperty = new PropertyConfig(path);
        utilities = new UiUtilitiesObjects();
    }

    protected static void navigateToUrl(String url) {
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
