package test.flightPageRegistration.test;

import listeners.AllureListener;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import test.FactoryBaseTest;
import test.flightPageRegistration.ShareComponents;
import utilities.errors.Category;
import utilities.errors.Reasons;
import utilities.errors.Severity;

@Listeners(AllureListener.class)
public class BookFlightTest extends FactoryBaseTest {

    private ShareComponents shareComponents;

    @BeforeClass
    public void init() {
        this.shareComponents = new ShareComponents();
    }

    @Test(priority = 1)
    public void registrationPage() {
       // shareComponents.shareComponents(utilities.wrapper((action1, action2) -> {
          //  action2.getLeft().goTo(getProperty.url);
           // action2.getLeft().enterUserDetails("selenium", "docker");
          //  action2.getLeft().enterUserCredentials("selenium", "docker");
          //  action2.getLeft().submit();
        //}, WebDriverException.class, true, Reasons.APP, Category.INTERNAL, Severity.HIGH));
    }

    @Test(priority = 2, dependsOnMethods = "registrationPage")
    public void registrationConfirmationPage() {
        //shareComponents.shareComponents(utilities.wrapper((action1, action2) -> {
          //  action2.getRight().goToFlightDetailsSignLink();
       // }, WebDriverException.class, true));
    }
}
