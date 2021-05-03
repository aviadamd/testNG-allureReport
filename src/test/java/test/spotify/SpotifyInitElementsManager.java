package test.spotify;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pagesInit.InitElementsSteps;
import test.accountManagement.AccountManagementUi;
import test.flightPageRegistration.FlightUi;
import test.spotify.mobile.SpotifyMobileUi;
import test.spotify.web.SpotifyWebUi;

public class SpotifyInitElementsManager extends SpotifyFactoryBase implements InitElementsSteps {


    @Override
    public void initElements(WebDriver driver, String desc) {
        try {
            if (isAndroidDriver(driver)) {
                spotifyMobileUi = new SpotifyMobileUi(driver);
            } else if (isWebDriver(driver)) {
                spotifyWebUi = new SpotifyWebUi(driver);
            }
        } catch (Exception e) {
            throw new RuntimeException(desc + " init page general error : " + e.getMessage());
        }
    }

    @Override
    public boolean isAndroidDriver(WebDriver driver) {
        return driver instanceof AndroidDriver<?>;
    }

    @Override
    public boolean isWebDriver(WebDriver driver) {
        return driver instanceof ChromeDriver
                ||  driver instanceof FirefoxDriver;
    }
}
