package test.spotify.mobile;

import org.openqa.selenium.WebDriver;
import test.spotify.web.pages.MobileLandingScreenRegistrationPage;

public class SpotifyMobileUi {

    public MobileLandingScreenRegistrationPage landingScreenRegistrationPage;

    public SpotifyMobileUi(WebDriver driver) {
        this.landingScreenRegistrationPage = new MobileLandingScreenRegistrationPage(driver);
    }
}
