package base.driverManager.InitDrivers.web;

import base.Base;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Proxy;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.Set;

/**
 * an web shared class to give helper methods that web clients have in common
 * the chrome driver and firefox driver use...
 */
@Slf4j
public class SharedWebManager extends Base {

    private static boolean isProxy() {
        return getProperty.isRunProxy.equals("true");
    }

    @SneakyThrows
    public static Proxy seleniumProxy() {
        if (isProxy()) {
            mobProxy = new BrowserMobProxyServer();
            mobProxy.setTrustAllServers(true);
            mobProxy.start(0);
            mobProxy.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
            mobProxy.enableHarCaptureTypes(
                    CaptureType.REQUEST_HEADERS, CaptureType.REQUEST_CONTENT,
                    CaptureType.RESPONSE_BINARY_CONTENT, CaptureType.REQUEST_COOKIES,
                    CaptureType.RESPONSE_HEADERS, CaptureType.RESPONSE_CONTENT,
                    CaptureType.RESPONSE_COOKIES
            );
            seleniumProxy = ClientUtil.createSeleniumProxy(mobProxy);
            String hostIp = InetAddress.getLocalHost().getHostAddress();
            seleniumProxy.setHttpProxy(hostIp + ":" + mobProxy.getPort());
            seleniumProxy.setSslProxy(hostIp + ":" + mobProxy.getPort());
            mobProxy.addRequestFilter((request, contents, messageInfo) -> {
                System.out.println(request.getUri());
                System.out.println(request.getMethod().toString());
                HttpHeaders headers = request.headers();
                Iterator<?> test = headers.iterator();
                Object ob;

                while (test.hasNext()) {
                    ob = test.next();
                    System.out.println(ob.toString());
                }

                return null;
            });

            return seleniumProxy;
        }

        return null;
    }

    public static void stopProxy() {
        if (isProxy()) {
            if (mobProxy != null && ((BrowserMobProxyServer) mobProxy).isStopped()) {
                mobProxy.stop();
            }
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
