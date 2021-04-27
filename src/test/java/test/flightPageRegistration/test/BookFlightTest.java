package test.flightPageRegistration.test;

import listeners.AllureListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.FactoryBaseTest;
import test.flightPageRegistration.components.RegistrationComponent;
import test.flightPageRegistration.components.RegistrationConfirmationComponent;

@Listeners(AllureListener.class)
public class  BookFlightTest extends FactoryBaseTest {

    private RegistrationComponent registrationComponent;
    private RegistrationConfirmationComponent registrationConfirmationPage;

    @BeforeClass
    public void init() {
        this.registrationComponent = new RegistrationComponent();
        this.registrationConfirmationPage = new RegistrationConfirmationComponent();
    }

    @Test(priority = 1)
    public void registrationPage() {
        registrationComponent.goTo(getProperty.url);
        registrationComponent.enterUserDetails("selenium", "docker");
        registrationComponent.enterUserCredentials("selenium", "docker");
        registrationComponent.submit();
    }

//    @Test
//    public void registration() {
//        registrationComponent.registrations("create registration action", (a,b) -> {
//            a.getLeft().goTo(getProperty.url);
//            a.getRight().print("about to enter details");
//            a.getLeft().enterUserDetails("selenium", "docker");
//            a.getLeft().enterUserCredentials("selenium", "docker");
//            a.getLeft().submit();
//        });
//    }

    @Test(priority = 2, dependsOnMethods = "registrationPage")
    public void registrationConfirmationPage() {
        registrationConfirmationPage.goToFlightDetailsSignLink();
    }
}
