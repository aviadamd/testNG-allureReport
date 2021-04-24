package test;

import base.Base;
import base.anontations.Author;
import base.driverManager.factory.DriverManager;
import base.driverManager.factory.DriverManagerFactory;
import io.qameta.allure.Description;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import test.accountManagement.AccountManagementUi;
import test.flightPageRegistration.FlightUi;

/**
 * this class init the driver factory pattern
 * beforeClass() int the driver from the DriverManagerFactory
 * afterClass() close driver session
 */
@Author(desc = "aviad")
public class FactoryBaseTest extends Base {

    private DriverManager driverManager;
    public static FlightUi flightUi;
    public static AccountManagementUi aManUi;

    /**
     * Init the driver manager object to the getManager method from DriverManger instance
     * Than placement the driver object instance , chrome firefox android driver
     */
    @BeforeClass(description = "start sessions")
    @Description(value = "start session before test")
    public void beforeClass() {
        String platform = getProperty.platformType;
        driverManager = DriverManagerFactory.getManager(platform);
        driver = driverManager.getDriver();
        new InitElementsManager().initElements(driver, platform);
    }

    @AfterClass(description = "quit sessions")
    @Description(value = "quit session after test")
    public void afterClass() {
        driverManager.quitDriver();
    }
}
