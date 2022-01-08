package base;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import ru.yandex.qatools.ashot.Screenshot;
import utilities.UiUtilitiesObjects;

@Slf4j
public class BaseMobile {

    public static WebDriver driver;
    public static Proxy seleniumProxy;
    public static BrowserMobProxy mobProxy;
    public static AppiumDriverLocalService server;
    public static UiUtilitiesObjects utilities;
    public static PropertyConfig getProperty;
    public static Screenshot imageScreenShot;

    @BeforeClass
    public void initClass() {
        String path = "/src/main/resources/config.properties";
        getProperty = new PropertyConfig(path);
    }
}
