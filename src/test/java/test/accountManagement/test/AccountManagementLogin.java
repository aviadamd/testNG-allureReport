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
    }
}
