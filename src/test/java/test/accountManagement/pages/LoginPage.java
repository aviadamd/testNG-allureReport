package test.accountManagement.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import pagesInit.InitsPagesManager;
import pagesInit.PageType;

public class LoginPage extends InitsPagesManager {

    public LoginPage(WebDriver driver) { super(PageType.MOBILE, driver); }

    private final String appId = "com.ideomobile.hapoalim:id/";

    @AndroidFindBy(id = appId + "next_time")
    public MobileElement onBoardingPreview_nextTime;

    @AndroidFindBy(id = appId + "login_container")
    public MobileElement mainView;

    @AndroidFindBy(id = appId + "login_user_name_view_automation")
    public MobileElement userNameEditText;

    @AndroidFindBy(id = appId + "login_password_view_automation")
    public MobileElement userPasswordEditText;

}
