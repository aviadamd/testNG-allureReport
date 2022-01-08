package base.driverManager.InitDrivers;

import base.driverManager.DriverManager;
import base.driverManager.PlatformsType;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class RemoteDriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        switch (getProperty.remotePlatformType) {
            case PlatformsType.CHROME:
                desiredCapabilities = DesiredCapabilities.chrome();
                break;
            case PlatformsType.FIREFOX:
                desiredCapabilities = DesiredCapabilities.firefox();
                break;
            case PlatformsType.ANDROID:
                desiredCapabilities = DesiredCapabilities.android();
                break;
            case PlatformsType.IOS:
                desiredCapabilities = DesiredCapabilities.safari();
                break;
        }
        driver = remoteDriverInitiation(desiredCapabilities);
    }

    @Override
    protected boolean isServerRunning() {
        return driver != null;
    }

    @Override
    protected void stopDriver() {
        driver.quit();
    }

    @SneakyThrows
    private WebDriver remoteDriverInitiation(DesiredCapabilities capabilities) {
        URL url = new URL("http://"+getProperty.hubHost+":4444/wd/hub");
        return new RemoteWebDriver(url, capabilities);
    }
}
