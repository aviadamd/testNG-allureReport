package test.flightPageRegistration.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.FactoryBaseTest;
import test.flightPageRegistration.components.RegistrationComponent;
import test.flightPageRegistration.components.RegistrationConfirmationComponent;

public class BookFlightTest extends FactoryBaseTest {

    private RegistrationComponent registrationComponent;
    private RegistrationConfirmationComponent registrationConfirmationPage;

    @BeforeClass
    public void init() {
        this.registrationComponent = new RegistrationComponent();
        this.registrationConfirmationPage = new RegistrationConfirmationComponent();
    }

    @Test(priority = 1)
    public void registrationPage() {
        registrationComponent.goTo();
        registrationComponent.enterUserDetails("selenium", "docker");
        registrationComponent.enterUserCredentials("selenium", "docker");
        registrationComponent.submit();
    }

    @Test(priority = 2, dependsOnMethods = "registrationPage")
    public void registrationConfirmationPage() {
        registrationConfirmationPage.goToFlightDetailsSignLink();
        utilities.uiActions().click(flightUi.registrationPage.submitBtn);
    }
}
