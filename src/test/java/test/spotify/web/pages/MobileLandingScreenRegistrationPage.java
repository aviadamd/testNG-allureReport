package test.spotify.web.pages;

import org.openqa.selenium.WebDriver;
import pagesInit.PageType;
import pagesInit.PagesManager;

public class MobileLandingScreenRegistrationPage extends PagesManager {

    public MobileLandingScreenRegistrationPage(WebDriver driver) {
        super(PageType.MOBILE, driver);
    }
}
