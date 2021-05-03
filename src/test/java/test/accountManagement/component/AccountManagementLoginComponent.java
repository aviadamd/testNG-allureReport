package test.accountManagement.component;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.logging.LogEntry;
import test.FactoryBaseTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AccountManagementLoginComponent extends FactoryBaseTest {

    @Step("load login page before test")
    public void loadLoginPage() {
        if (utilities.uiActions().elementPresented(
                aManUi.loginPage.onBoardingPreview_nextTime,15)) {
            utilities.uiActions().click(aManUi.loginPage.onBoardingPreview_nextTime);
        }

        for (String serviceOutPut : httpMessages()) {
            if (serviceOutPut.contains("ASSETS STORAGE CACHE")) {
                serviceOutPut = serviceOutPut.replace("ASSETS STORAGE CACHE","");
            }
            System.out.println(serviceOutPut);
        }

        utilities.verifications().load(aManUi.loginPage.mainView);
        utilities.uiActions().sendKeys(aManUi.loginPage.userNameEditText,"!wiz1017");
        utilities.uiActions().sendKeys(aManUi.loginPage.userPasswordEditText,"wiz123456");
        utilities.uiActions().click(aManUi.loginPage.enter);

        for (String serviceOutPut : httpMessages()) {
            if (serviceOutPut.contains("ASSETS STORAGE CACHE")) {
                serviceOutPut = serviceOutPut.replace("ASSETS STORAGE CACHE","");
            }
            System.out.println(serviceOutPut);
        }
    }

    private boolean isHttp(String input) {
        return input.contains("OkHttp");
    }

    private boolean isOk(String input) {
        return input.contains("200");
    }

    private List<String> httpMessages() {
        List<String> logs = new ArrayList<>();
        List<LogEntry> logEntries = driver.manage().logs().get("logcat").getAll();
        for (LogEntry entry : logEntries) {
            logs.add(entry.getMessage());
        }
        return logs;
    }
}
