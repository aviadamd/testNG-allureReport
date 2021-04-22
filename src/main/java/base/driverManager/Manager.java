package base.driverManager;

import org.openqa.selenium.WebDriver;

public interface Manager {
    WebDriver getDriver();
    void quitDriver();
}
