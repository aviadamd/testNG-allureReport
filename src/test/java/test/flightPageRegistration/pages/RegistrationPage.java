package test.flightPageRegistration.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pagesInit.InitsPagesManager;
import pagesInit.PageType;

public class RegistrationPage extends InitsPagesManager {

    public RegistrationPage(WebDriver driver){
        super(PageType.WEB, driver);
    }

    @FindBy(name = "firstName")
    public WebElement firstNameTxt;

    @FindBy(name = "lastName")
    public WebElement lastNameTxt;

    @FindBy(name = "email")
    public WebElement usernameTxt;

    @FindBy(name = "password")
    public WebElement passwordTxt;

    @FindBy(name = "confirmPassword")
    public WebElement confirmPasswordTxt;

    @FindBy(name = "register")
    public WebElement submitBtn;

}
