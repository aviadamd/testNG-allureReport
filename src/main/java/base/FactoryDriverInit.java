package base;

import base.driverManager.DriverManager;
import base.driverManager.DriverManagerFactory;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class FactoryDriverInit extends Base {

    private DriverManager driverManager;

    
    public WebDriver getDriver() {
        return driver;
    }

    @BeforeTest
    public void beforeTest() {
        if (driver == null) {
            initDriver();
        }
    }

    @BeforeClass(description = "start sessions")
    @Description(value = "start session before test")
    public void initDriver() {
        driverManager = DriverManagerFactory.getManager(getProperty.platformType);
        driver = driverManager.getDriver();
    }

    @AfterClass(description = "quit sessions")
    @Description(value = "quit session after test")
    public void afterClass() {
        driverManager.quitDriver();
    }
}
