package test;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import test.accountManagement.AccountManagementUi;

@Slf4j
public class InitElementsManager extends FactoryBaseTest {

    public void initElements(WebDriver driver, String desc) {
        try {
            aManUi = new AccountManagementUi(driver);
        } catch (Exception e) {
            throw new RuntimeException(desc + " init page general error : " + e.getMessage());
        }
    }
}
