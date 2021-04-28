package test.flightPageRegistration.components;

import base.Action;
import base.Steps;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import test.FactoryBaseTest;
import test.flightPageRegistration.FlightUi;
import test.flightPageRegistration.pages.RegistrationPage;
import utilities.UiUtilitiesObjects;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Slf4j
public class RegistrationComponent extends FactoryBaseTest implements Steps {

    @Override
    @Step("navigate to {0} base url")
    public void goTo(String baseUrl) {
        goToUrl(baseUrl);
        utilities.uiActions().elementPresented(flightUi.registrationPage.firstNameTxt,5);
    }

    @Override
    @Step("step {0} , action {1}")
    public void step(String name, Consumer<Pair<Action, UiUtilitiesObjects>> action) {
        log.info(name);
        action.accept(Pair.of(new Action(),new UiUtilitiesObjects()));
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
        chain.accept(new UiUtilitiesObjects(),new RegistrationPage(driver));
    }

    public RegistrationComponent registration(String print, Consumer<Triple<RegistrationComponent
            , UiUtilitiesObjects, FlightUi>> testAction) {
        log.info(print);
        testAction.accept(Triple.of(new RegistrationComponent(), new UiUtilitiesObjects(), new FlightUi(driver)));
        return this;
    }

    public RegistrationComponent registrations(String name, BiConsumer<Pair<RegistrationComponent, Action>,
            Pair<UiUtilitiesObjects, FlightUi>> testAction) {
        log.info(name);
        testAction.accept(Pair.of(new RegistrationComponent(), new Action()), Pair.of(new UiUtilitiesObjects(), new FlightUi(driver)));
        return this;
    }

    public BiConsumer<UiUtilitiesObjects, RegistrationPage> verifyRegistrationPage = (action,page) -> {
        action.uiActions().elementPresented(page.usernameTxt,5);
        action.uiActions().elementPresented(page.passwordTxt,5);
        action.uiActions().elementPresented(page.confirmPasswordTxt,5);
    };

    public BiConsumer<UiUtilitiesObjects, RegistrationPage> submit = (action,page) -> {
        action.verifications().load(page.submitBtn);
        action.uiActions().click(page.submitBtn);
    };

}