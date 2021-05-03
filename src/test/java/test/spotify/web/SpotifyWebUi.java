package test.spotify.web;

import org.openqa.selenium.WebDriver;
import test.spotify.mobile.pages.WebLandingScreenRegistrationsPage;

public class SpotifyWebUi {

    public WebLandingScreenRegistrationsPage landingScreenRegistrationsPage;
    public SpotifyWebUi(WebDriver driver) {
        this.landingScreenRegistrationsPage = new WebLandingScreenRegistrationsPage(driver);
    }
}
