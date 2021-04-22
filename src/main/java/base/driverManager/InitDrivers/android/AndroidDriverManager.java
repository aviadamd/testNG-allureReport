package base.driverManager.InitDrivers.android;

import base.Base;
import base.driverManager.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.mitmproxy.InterceptedMessage;
import io.appium.mitmproxy.MitmproxyJava;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Description;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Description("use as a class that extends DriverManager abstract class template")
public class AndroidDriverManager extends DriverManager {

    private static final ThreadLocal<MitmproxyJava> proxy = new ThreadLocal<>();
    public static List<InterceptedMessage> messages = new ArrayList<>();

    @Override
    protected void createDriver() {
        driver = startAppiumServer();
        ((AndroidDriver<?>)driver).resetApp();
        startProxy();
    }

    @Override
    @SneakyThrows
    protected void stopDriver() {
        driver.quit();
        server.stop();
        proxy.remove();
    }

    private static WebDriver startAppiumServer() {
        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", getProperty.localBin + System.getenv("PATH"));
        environment.put("ANDROID_HOME", getProperty.androidSdk);
        DesiredCapabilities capabilities = initCapabilities();
        server = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File(getProperty.nodeJs))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error")
                .withArgument(GeneralServerFlag.RELAXED_SECURITY)
                .withEnvironment(environment)
                .withCapabilities(capabilities)
                .withStartUpTimeOut(20, TimeUnit.SECONDS));
        server.start();
        return new AndroidDriver<>(server.getUrl(), capabilities);
    }

    private static DesiredCapabilities initCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"appium");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"device");
        capabilities.setCapability(MobileCapabilityType.APP, getProperty.appPath);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
        return capabilities;
    }

    private void startProxy() {
        try {
            proxy.set(new MitmproxyJava(getProperty.mitProxyPath,(InterceptedMessage proxy) -> {
                System.out.println("request " + proxy.getRequest().getUrl());
                System.out.println("response " + proxy.getResponse().getStatusCode());
                messages.add(proxy);
                return proxy;
            }, 8081, null));
            proxy.get().start();
        } catch (TimeoutException | IOException e) {
           log.error(e.getMessage());
        }
    }
}
