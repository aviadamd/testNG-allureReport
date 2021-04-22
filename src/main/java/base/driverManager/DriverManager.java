package base.driverManager;

import base.Base;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.SneakyThrows;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

import java.net.InetAddress;
import java.util.Iterator;

public abstract class DriverManager extends Base implements Manager {

    protected abstract void createDriver();
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
