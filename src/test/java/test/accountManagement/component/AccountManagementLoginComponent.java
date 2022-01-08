package test.accountManagement.component;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import test.FactoryBaseTest;

@Slf4j
public class AccountManagementLoginComponent extends FactoryBaseTest {

    @Step("load login page before test")
    public void loadLoginPage() {
        utilities.uiActions().clickOptional(aManUi.loginPage.onBoardingPreview_nextTime);
        utilities.verifications().load(aManUi.loginPage.mainView);
        utilities.uiActions().sendKeys(aManUi.loginPage.userNameEditText,"!wiz1017");
        utilities.uiActions().sendKeys(aManUi.loginPage.userPasswordEditText,"wiz123456");
        utilities.uiActions().click(aManUi.loginPage.enter);
    }

}
