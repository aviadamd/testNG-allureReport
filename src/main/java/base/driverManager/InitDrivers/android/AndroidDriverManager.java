package base.driverManager.InitDrivers.android;

import base.driverManager.DriverManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.mitmproxy.InterceptedMessage;
import io.appium.mitmproxy.MitmproxyJava;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Description;
import java.io.*;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static java.util.Optional.ofNullable;

@Slf4j
/**
 * implement the factory pattern
 * this class extend the driver manager to implements
 * createDriver() method and init android driver
 * stopDriver() stop chrome driver
 */
@Description("use as a class that extends DriverManager abstract class template")
public class AndroidDriverManager extends DriverManager {

    private static final ThreadLocal<MitmproxyJava> proxy = new ThreadLocal<>();
    public static List<InterceptedMessage> messages = new ArrayList<>();

    @Override
    protected void createDriver() {
        driver = initAppiumServerAndDriver();
        ((AndroidDriver<?>)driver).resetApp();
        startProxy();
    }

    @Override
    @SneakyThrows
    protected void stopDriver() {
        ofNullable(driver).ifPresent(WebDriver::quit);
        server.stop();
        stopProxy();
    }

    @Override
    public boolean isServerRunning() {
        return server.isRunning() || server != null || checkIfServerIsRunning(4444);
    }

    private static WebDriver initAppiumServerAndDriver() {
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
        return androidDriver(server.getUrl(), capabilities);
    }

    private static AndroidDriver<MobileElement> androidDriver(URL url, Capabilities caps) {
        return new AndroidDriver<>(url,caps);
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
            if (getProperty.isRunProxy.equals("true")) {
                proxy.set(new MitmproxyJava(getProperty.mitProxyPath, (InterceptedMessage proxy) -> {
                    System.out.println("request " + proxy.getRequest().getUrl());
                    System.out.println("response " + proxy.getResponse().getStatusCode());
                    messages.add(proxy);
                    return proxy;
                }, 8081, null));
                proxy.get().start();
            }
        } catch (TimeoutException | IOException e) {
           log.error(e.getMessage());
        }
    }

    private boolean isHttp(String input) {
        return input.contains("OkHttp");
    }

    private boolean isOk(String input) {
        return input.contains("200");
    }

    private List<String> httpMessages() {
        List<String> logs = new ArrayList<>();
        List<LogEntry> logEntries = driver.manage().logs().get("logcat").getAll();
        for (LogEntry entry : logEntries) {
            if (isHttp(entry.getMessage())) {
                logs.add(entry.getMessage());
            }
        }
        return logs;
    }

    private void stopProxy() {
        if (getProperty.isRunProxy.equals("true")) {
            proxy.remove();
        }
    }

    public boolean checkIfServerIsRunning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        }
        return isServerRunning;
    }
}
