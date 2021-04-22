package test;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pagesInit.InitElements;
import test.accountManagement.AccountManagementUi;
import test.flightPageRegistration.FlightUi;

@Slf4j
public class InitElementsManager extends FactoryBaseTest implements InitElements {

    @Override
    @Description("init the {0} and init elements before the test")
    public void initElements(WebDriver driver) {
        try {
            if (isAndroidDriver(driver)) {
                aManUi = new AccountManagementUi(driver);
                log.info("init " + aManUi.getClass().getName());
            } else if (isWebDriver(driver)) {
                flightUi = new FlightUi(driver);
                log.info("init " + flightUi.getClass().getName());
            }
        } catch (Exception e) {
            throw new RuntimeException("init pages error : " + e.getMessage());
        }
    }

    @Override
    public boolean isAndroidDriver(WebDriver driver) {
        if (driver instanceof AndroidDriver<?>) {
            log.info("init " + driver.getClass().getName());
            return true;
        }
        return false;
    }

    @Override
    public boolean isWebDriver(WebDriver driver) {
        if (driver instanceof ChromeDriver || driver instanceof FirefoxDriver) {
            log.info("init " + driver.getClass().getName());
            return true;
        }
        return false;
    }

}
