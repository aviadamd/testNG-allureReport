package base.driverManager.InitDrivers.web;

import base.Base;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.openqa.selenium.Cookie;

import java.util.Set;

@Slf4j
public class SharedWebManager extends Base {

    public void stopProxy() {
        if (mobProxy != null && ((BrowserMobProxyServer) mobProxy).isStopped()) {
            mobProxy.stop();
        }
    }

    public void clearCache() {
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            driver.manage().deleteCookieNamed(cookie.getName());
        }
        cookies = driver.manage().getCookies();
        if (cookies.size() == 0) {
            log.info("clear browser cache");
        }
    }
}
