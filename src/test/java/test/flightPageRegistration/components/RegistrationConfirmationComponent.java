package test.flightPageRegistration.components;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import test.FactoryBaseTest;

@Slf4j
public class RegistrationConfirmationComponent extends FactoryBaseTest {

    @Step("go to flight details sign link")
    public void goToFlightDetailsSignLink() {
        utilities.uiActions().elementPresented(flightUi.registrationConfirmationPage.signinLink,5);
        utilities.sharedActions().click(flightUi.registrationConfirmationPage.signinLink);
    }

}
