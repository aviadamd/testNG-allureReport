package test.spotify.web.components;

import org.openqa.selenium.WebDriver;
import pagesInit.PageType;
import pagesInit.PagesManager;

public class MobileLandingScreenRegistration extends PagesManager {
    public MobileLandingScreenRegistration(WebDriver driver) {
        super(PageType.WEB, driver);
    }
}
