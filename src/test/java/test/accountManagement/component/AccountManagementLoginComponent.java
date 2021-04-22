package test.accountManagement.component;

import io.qameta.allure.Step;
import test.FactoryBaseTest;

public class AccountManagementLoginComponent extends FactoryBaseTest {

    @Step("load login page before test")
    public void loadLoginPage() {
        if (utilities.uiActions().elementPresented(
                aManUi.loginPage.onBoardingPreview_nextTime,5)) {
            utilities.uiActions().click(aManUi.loginPage.onBoardingPreview_nextTime);
        }
        utilities.verifications().load(aManUi.loginPage.mainView);
    }
}
