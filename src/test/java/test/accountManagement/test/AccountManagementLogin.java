package test.accountManagement.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.FactoryBaseTest;
import test.accountManagement.component.AccountManagementLoginComponent;

public class AccountManagementLogin extends FactoryBaseTest {

    private AccountManagementLoginComponent loginComponent;

    @BeforeClass
    public void init() {
        this.loginComponent = new AccountManagementLoginComponent();
    }

    @Test
    public void loginTest() {
        loginComponent.loadLoginPage();
        utilities.uiActions().perform("create login test", (action, ver) -> {
            if (action.elementPresented(aManUi.loginPage.onBoardingPreview_nextTime,5)) {
                action.click(aManUi.loginPage.onBoardingPreview_nextTime);
            }
            ver.load(aManUi.loginPage.mainView);
        });

        utilities.uiActions().perform("create another test",(actions) -> {
            if (actions.uiActions().elementPresented(aManUi.loginPage.onBoardingPreview_nextTime,5)) {
                actions.uiActions().click(aManUi.loginPage.onBoardingPreview_nextTime);
            }
            actions.verifications().load(aManUi.loginPage.mainView);
        });
    }

}
