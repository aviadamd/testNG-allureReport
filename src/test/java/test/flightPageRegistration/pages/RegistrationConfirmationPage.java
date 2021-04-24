package test.flightPageRegistration.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pagesInit.InitsPagesManager;
import pagesInit.PageType;

public class RegistrationConfirmationPage extends InitsPagesManager {

    public RegistrationConfirmationPage(WebDriver driver) {
        super(PageType.WEB, driver);
    }

    @FindBy(partialLinkText = "sign-in")
    public WebElement signinLink;

    @FindBy(linkText = "Flights")
    public WebElement flightsLink;

}
