package test.accountManagement.component;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import test.FactoryBaseTest;

@Slf4j
public class AccountManagementLoginComponent extends FactoryBaseTest {

    @Step("load login page before test")
    public void loadLoginPage() {
        utilities.sharedActions().clickOptional(aManUi.loginPage.onBoardingPreview_nextTime);
        utilities.sharedMobileUtils().printService();
        utilities.verifications().load(aManUi.loginPage.mainView);
        utilities.sharedActions().sendKeys(aManUi.loginPage.userNameEditText,"!wiz1017");
        utilities.sharedActions().sendKeys(aManUi.loginPage.userPasswordEditText,"wiz123456");
        utilities.sharedActions().click(aManUi.loginPage.enter);
        utilities.sharedMobileUtils().printService();
    }

}
