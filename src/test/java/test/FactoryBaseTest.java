package test;

import base.Base;
import base.anontations.Author;
import base.driverManager.DriverManager;
import base.driverManager.DriverManagerFactory;
import io.qameta.allure.Description;
import listeners.AllureListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import test.accountManagement.AccountManagementUi;
import test.flightPageRegistration.FlightUi;

@Author(desc = "aviad")
@Listeners(AllureListener.class)
public class FactoryBaseTest extends Base {

    private DriverManager driverManager;
    public static FlightUi flightUi;
    public static AccountManagementUi aManUi;

    @BeforeClass(description = "start sessions")
    @Description(value = "start session")
    public void beforeClass() {
        driverManager = DriverManagerFactory.getManager(getProperty.platformType);
        driver = driverManager.getDriver();
        new InitElementsManager().initElements(driver);
    }

    @AfterClass(description = "quit sessions")
    @Description(value = "quit session")
    public void afterClass() {
        driverManager.quitDriver();
    }
}
