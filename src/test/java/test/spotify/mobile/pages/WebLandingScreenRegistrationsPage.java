package test.spotify.mobile.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pagesInit.PageType;
import pagesInit.PagesManager;

public class WebLandingScreenRegistrationsPage extends PagesManager {
    public WebLandingScreenRegistrationsPage(WebDriver driver) {
        super(PageType.WEB, driver);
    }

    ////*[@id="__next"]/div[1]/header/div
    @FindBy(xpath = "//*[resource-id='multi-language-link alert-link'")
    public WebElement toolBar;
}
