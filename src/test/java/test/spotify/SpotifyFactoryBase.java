package test.spotify;

import base.Base;
import base.driverManager.DriverManager;
import base.driverManager.DriverManagerFactory;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import test.InitElementsManager;
import test.spotify.mobile.SpotifyMobileUi;
import test.spotify.web.SpotifyWebUi;

public class SpotifyFactoryBase extends Base {

    public static SpotifyWebUi spotifyWebUi;
    public static SpotifyMobileUi spotifyMobileUi;
    private DriverManager driverManager;
    private final ThreadLocal<WebDriver> getDriver = new ThreadLocal<>();

    /**
     * Init the driver manager object to the getManager method from DriverManger instance
     * Than placement the driver object instance , chrome firefox android driver
     */
    @BeforeClass(description = "start sessions")
    @Description(value = "start session before test")
    public void beforeClass() {
        driverManager = DriverManagerFactory.getManager(getProperty.platformType);
        getDriver.set(driverManager.getDriver());
        driver = getDriver.get();
        new SpotifyInitElementsManager().initElements(driver, getProperty.platformType);
    }

    @AfterClass(description = "quit sessions")
    @Description(value = "quit session after test")
    public void afterClass() {
        driverManager.quitDriver();
        getDriver.remove();
    }
}
