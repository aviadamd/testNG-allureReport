package test.accountManagement.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pagesInit.InitsPagesManager;
import pagesInit.PageType;

public class LoginPage extends InitsPagesManager {

    public LoginPage(WebDriver driver) { super(PageType.MOBILE, driver); }

    private final String appId = "com.ideomobile.hapoalim:id/";

    @AndroidFindBy(id = appId + "next_time")
    public WebElement onBoardingPreview_nextTime;

    @AndroidFindBy(id = appId + "login_container")
    public WebElement mainView;

    @AndroidFindBy(id = appId + "login_user_name_view_automation")
    public WebElement userNameEditText;

    @AndroidFindBy(id = appId + "login_password_view_automation")
    public WebElement userPasswordEditText;

}
