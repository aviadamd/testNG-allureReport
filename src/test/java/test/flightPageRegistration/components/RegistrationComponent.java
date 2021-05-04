package test.flightPageRegistration.components;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import test.FactoryBaseTest;
import test.flightPageRegistration.FlightUi;
import test.flightPageRegistration.pages.RegistrationPage;
import utilities.UiUtilitiesObjects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Slf4j
public class RegistrationComponent extends FactoryBaseTest {

    @Step("navigate to {0} base url")
    public void goTo(String baseUrl) {
        utilities.uiActions().goToUrl(baseUrl);
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
        BiConsumer<UiUtilitiesObjects,RegistrationPage> chain = verifyRegistrationPage.andThen(submit);
        chain.accept(new UiUtilitiesObjects(), new RegistrationPage(driver));
        //actions.click(flightUi.registrationPage.submitBtn);
    }

    public RegistrationComponent registrations(String name, Consumer<Triple<RegistrationComponent,UiUtilitiesObjects, FlightUi>> testAction) {
        log.info(name);
        testAction.accept(Triple.of(new RegistrationComponent(),new UiUtilitiesObjects(), new FlightUi(driver)));
        return new RegistrationComponent();
    }

    public BiConsumer<UiUtilitiesObjects, RegistrationPage> verifyRegistrationPage = (action,page) -> {
        action.uiActions().elementPresented(page.usernameTxt,5);
        action.uiActions().elementPresented(page.passwordTxt,5);
        action.uiActions().elementPresented(page.confirmPasswordTxt,5);
    };

    private final BiConsumer<UiUtilitiesObjects, RegistrationPage> submit = (action,page) -> {
        action.verifications().load(page.submitBtn);
        action.uiActions().click(page.submitBtn);
    };
}