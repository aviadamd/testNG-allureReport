package test;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import test.accountManagement.AccountManagementUi;
import test.flightPageRegistration.FlightUi;

public class InitElementsManager extends FactoryBaseTest {

    public void initElements(WebDriver driver) {
        if (driver != null) {
            if (isAndroid(driver)) {
                aManUi = new AccountManagementUi(driver);
            } else if (isWeb(driver)) {
                flightUi = new FlightUi(driver);
            } else {
                throw new RuntimeException("driver initiation error");
            }
        } else throw new RuntimeException("driver is null");
    }

    private boolean isAndroid(WebDriver driver) {
        return driver instanceof AndroidDriver<?>;
    }

    private boolean isWeb(WebDriver driver) {
        return driver instanceof ChromeDriver || driver instanceof FirefoxDriver;
    }
}
