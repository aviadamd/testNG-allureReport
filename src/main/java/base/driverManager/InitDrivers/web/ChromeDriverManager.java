package base.driverManager.InitDrivers.web;

import base.driverManager.factory.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Description;
import java.util.function.Supplier;
import static base.driverManager.InitDrivers.web.SharedWebManager.seleniumProxy;
import static java.util.Collections.singletonList;

/**
 * implement the factory pattern
 * this class extend the driver manager to implements
 * createDriver() method and init the chrome driver
 * stopDriver() stop chrome driver
 */
@Description("use as a class that extends DriverManager abstract class template")
public class ChromeDriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        chromeDriverSupplier.get();
        new SharedWebManager().clearCache();
    }

    @Override
    protected void stopDriver() {
        SharedWebManager.stopProxy();
        driver.quit();
    }

    private final Supplier<WebDriver> chromeDriverSupplier = () -> {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions(seleniumProxy()));
        return driver;
    };

    private ChromeOptions chromeOptions(Proxy seleniumProxy) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("disable-notifications");
        options.addArguments("disable-infobars");
        options.addArguments("start-maximized");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", singletonList("enable-automation"));
        DesiredCapabilities seleniumCapabilities = new DesiredCapabilities();
        if (getProperty.isRunProxy.equals("true"))
            seleniumCapabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        seleniumCapabilities = DesiredCapabilities.chrome();
        seleniumCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.merge(seleniumCapabilities);
        return options;
    }
}
