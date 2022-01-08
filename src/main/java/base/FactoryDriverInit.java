package base;

import base.driverManager.DriverManager;
import base.driverManager.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class FactoryDriverInit extends BaseMobile {

    private DriverManager driverManager;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeTest(description = "init driver")
    public void beforeTest() {
        if (driver == null) {
            initDriver();
        }
    }

    @BeforeClass(description = "start sessions")
    public void initDriver() {
        driverManager = DriverManagerFactory.getManager(getProperty.platformType);
        driver = driverManager.getDriver();
    }

    @AfterClass(description = "quit sessions")
    public void afterClass() {

        driverManager.quitDriver();
    }
}
