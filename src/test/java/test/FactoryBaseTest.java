package test;

import base.Base;
import base.anontations.Author;
import base.driverManager.DriverManager;
import base.driverManager.DriverManagerFactory;
import io.qameta.allure.Step;
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

    @Step("start session")
    @BeforeClass(description = "start sessions")
    public void beforeClass() {
        driverManager = DriverManagerFactory.getManager(getProperty.platformType);
        driver = driverManager.getDriver();
        new InitElementsManager().initElements(driver);
    }

    @Step("quit session")
    @AfterClass(description = "quit sessions")
    public void afterClass() {
        driverManager.quitDriver();
    }
}
