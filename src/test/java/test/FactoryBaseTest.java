package test;

import base.Base;
import base.anontations.Author;
import base.driverManager.DriverManager;
import base.driverManager.DriverManagerFactory;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import test.accountManagement.AccountManagementUi;
import test.flightPageRegistration.FlightUi;

/**
 * this class init the driver factory pattern
 * beforeClass() int the driver from the DriverManagerFactory
 * afterClass() close driver session
 */
@Author(desc = "")
public class FactoryBaseTest extends Base {

    private DriverManager driverManager;
    public static FlightUi flightUi;
    public static AccountManagementUi aManUi;
    private final ThreadLocal<WebDriver> getDriver = new ThreadLocal<>();

    /**
     * Init the driver manager object to the getManager method from DriverManger instance
     * Than placement the driver object instance , chrome firefox android driver
     */
    @BeforeClass(description = "start sessions")
    @Description(value = "start session before test")
    public void beforeClass() {
        driverManager = DriverManagerFactory.getManager(getProperty.platformType);
        getDriver.set(driverManager.getDriver());
        driver = getDriver.get();
        new InitElementsManager().initElements(driver, getProperty.platformType);
    }

    @AfterClass(description = "quit sessions")
    @Description(value = "quit session after test")
    public void afterClass() {
        driverManager.quitDriver();
    }
}
