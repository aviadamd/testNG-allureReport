package base.driverManager;

import base.BaseMobile;
import org.openqa.selenium.WebDriver;

/**
 * Factory Pattern is one of the creation Patterns.
 * It is mostly used when we need to create an object from one of several
 * possible classes that share a common super class
 * implements an interface.
 * It creates objects without exposing the instantiation logic to the user
 *
 * I have implements the manager interface for add more
 * abstraction on the get driver and quit driver
 */
public abstract class DriverManager extends BaseMobile implements Manager {

    /**
     * every class that extend this abstract class will get two implementations to do
     * createDriver();
     * stopDriver();
     */
    protected abstract void createDriver();
    protected abstract boolean isServerRunning();
    protected abstract void stopDriver();

    @Override
    public WebDriver getDriver() {
        if (driver == null) {
            createDriver();
        }
        return driver;
    }

    @Override
    public void quitDriver() {
        if (driver != null) {
            stopDriver();
            driver = null;
        }
    }

}
