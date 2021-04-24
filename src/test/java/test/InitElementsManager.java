package test;

import io.appium.java_client.android.AndroidDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pagesInit.InitElementsSteps;
import test.accountManagement.AccountManagementUi;
import test.flightPageRegistration.FlightUi;

/**
 * Implement the init elements interface for
 * @override initElements()
 * @override isAndroidDriver()
 * @override isWebDriver()
 */
@Slf4j
public class InitElementsManager extends FactoryBaseTest implements InitElementsSteps {

    @Override
    public void initElements(WebDriver driver, String desc) {
        try {
            if (isAndroidDriver(driver)) {
                aManUi = new AccountManagementUi(driver);
                log.info("init " + aManUi.getClass().getName() + " on " + desc);
            } else if (isWebDriver(driver)) {
                flightUi = new FlightUi(driver);
                log.info("init " + flightUi.getClass().getName() + " on " + desc);
            }
        } catch (Exception e) {
            throw new RuntimeException(desc + " init page general error : " + e.getMessage());
        }
    }

    @Override
    public boolean isAndroidDriver(WebDriver driver) {
        return driver instanceof AndroidDriver<?>;
    }

    @Override
    public boolean isWebDriver(WebDriver driver) {
        return driver instanceof ChromeDriver || driver instanceof FirefoxDriver;
    }

}
