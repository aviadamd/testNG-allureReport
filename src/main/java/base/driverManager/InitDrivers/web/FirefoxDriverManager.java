package base.driverManager.InitDrivers.web;

import base.driverManager.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Description;
import java.util.function.Supplier;
import static base.driverManager.InitDrivers.web.SharedWebManager.seleniumProxy;

/**
 * implement the factory pattern
 * this class extend the driver manager to implements
 * createDriver() method and init the firefox driver
 * stopDriver() stop chrome driver
 */
@Description("use as a class that extends DriverManager abstract class template")
public class FirefoxDriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        firefoxDriverSupplier.get();
        SharedWebManager.clearCache(driver);
    }

    @Override
    protected void stopDriver() {
        SharedWebManager.stopDriver(driver);
    }

    private final Supplier<WebDriver> firefoxDriverSupplier = () -> {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(firefoxOptions(seleniumProxy()));
        return driver;
    };

    private static FirefoxOptions firefoxOptions(Proxy seleniumProxy) {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("disable-restore-session-state");
        firefoxOptions.addArguments("disable-notifications");
        firefoxOptions.addArguments("disable-infobars");
        firefoxOptions.addArguments("start-maximized");
        DesiredCapabilities seleniumCapabilities = new DesiredCapabilities();
        if (getProperty.isRunProxy.equals("true"))
            seleniumCapabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        seleniumCapabilities = DesiredCapabilities.firefox();
        seleniumCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        firefoxOptions.merge(seleniumCapabilities);
        return firefoxOptions;
    }
}
