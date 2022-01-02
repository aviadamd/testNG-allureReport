package test;

import base.FactoryDriverInit;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import test.accountManagement.AccountManagementUi;
import test.flightPageRegistration.FlightUi;

public class FactoryBaseTest extends FactoryDriverInit {

    public static FlightUi flightUi;
    public static AccountManagementUi aManUi;

    /**
     * Init the driver manager object to the getManager method from DriverManger instance
     * Than placement the driver object instance , chrome firefox android driver
     */
    @BeforeClass(description = "start sessions")
    @Description(value = "start session before test")
    public void initPages() {

        new InitElementsManager().initElements(driver, getProperty.platformType);
    }
}
