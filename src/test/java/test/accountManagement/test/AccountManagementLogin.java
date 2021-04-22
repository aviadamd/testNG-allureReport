package test.accountManagement.test;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.FactoryBaseTest;
import test.accountManagement.component.AccountManagementLoginComponent;

public class AccountManagementLogin extends FactoryBaseTest {

    private AccountManagementLoginComponent loginComponent;

    @BeforeClass
    @Description("init before test objects")
    public void init() {

        this.loginComponent = new AccountManagementLoginComponent();
    }

    @Test
    @Description("login test")
    public void loginTest() {
        utilities.uiActions().perform("create another test", actions -> {
            if (actions.uiActions().elementPresented(aManUi.loginPage.onBoardingPreview_nextTime,5)) {
                actions.uiActions().click(aManUi.loginPage.onBoardingPreview_nextTime);
            }
            actions.verifications().load(aManUi.loginPage.mainView);
        });
    }

}
