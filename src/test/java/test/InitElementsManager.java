package test;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import test.accountManagement.AccountManagementUi;
import test.flightPageRegistration.FlightUi;

public class InitElementsManager extends FactoryBaseTest {

    public void initElements(WebDriver driver) {
        try {
            if (isAndroidDriver(driver)) {
                aManUi = new AccountManagementUi(driver);
            } else if (isWebDriver(driver)) {
                flightUi = new FlightUi(driver);
            }
        } catch (Exception e) {
            throw new RuntimeException("init pages error : " + e.getMessage());
        }
    }

    private boolean isAndroidDriver(WebDriver driver) {
        return driver instanceof AndroidDriver<?>;
    }

    private boolean isWebDriver(WebDriver driver) {
        return driver instanceof ChromeDriver || driver instanceof FirefoxDriver;
    }
}
