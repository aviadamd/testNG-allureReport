package test.flightPageRegistration.components;

import base.driverManager.InitDrivers.web.WebConditions;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import test.FactoryBaseTest;

@Slf4j
public class RegistrationComponent extends FactoryBaseTest implements WebConditions {

    @Override
    @Step("navigate to {0} base url")
    public void goTo(String baseUrl) {
        navigateToUrl(baseUrl);
        utilities.uiActions().elementPresented(flightUi.registrationPage.firstNameTxt,5);
    }

    @Step("enter user details , first name {0}  last name {1}")
    public void enterUserDetails(String firstName, String lastName){
        utilities.uiActions().sendKeys(flightUi.registrationPage.firstNameTxt, firstName);
        utilities.uiActions().sendKeys(flightUi.registrationPage.lastNameTxt, lastName);
    }

    @Step("enter user credentials ,  user name {0}  password {1}")
    public void enterUserCredentials(String username, String password){
        utilities.uiActions().sendKeys(flightUi.registrationPage.usernameTxt, username);
        utilities.uiActions().sendKeys(flightUi.registrationPage.passwordTxt, password);
        utilities.uiActions().sendKeys(flightUi.registrationPage.confirmPasswordTxt, password);
    }

    @Step("submit step")
    public void submit(){
        utilities.uiActions()
                .click(flightUi.registrationPage.submitBtn);
    }

}
