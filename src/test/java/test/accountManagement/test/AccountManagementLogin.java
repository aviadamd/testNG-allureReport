package test.accountManagement.test;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.FactoryBaseTest;
import test.accountManagement.component.AccountManagementLoginComponent;

public class AccountManagementLogin extends FactoryBaseTest {

    private AccountManagementLoginComponent accountManagementLoginComponent;

    @BeforeClass
    @Description("init before test objects")
    public void init() {
        this.accountManagementLoginComponent = new AccountManagementLoginComponent();
    }

    @Test
    @Description("login test")
    public void loginTest() {
        accountManagementLoginComponent.loadLoginPage();
    }

}
