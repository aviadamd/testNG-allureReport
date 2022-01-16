package testBase;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Properties;

import static base.PropertyConfig.setProperties;

public class TestBaseConfig {

    public String localBin;
    public String androidSdk;
    public String nodeJs;
    public String appPath;
    public String hubHost;
    public String isRunProxy;
    public String mitProxyPath;
    private static Properties properties;

    @SneakyThrows
    public TestBaseConfig(String path) {
        properties = new Properties();
        final String pathStr = System.getProperty("user.dir") + path;

        try (FileInputStream fileInputStream = new FileInputStream(pathStr)) {
            properties.load(fileInputStream);
            localBin = setProperties("localBin");
            androidSdk = setProperties("androidSdk");
            nodeJs = setProperties("nodeJs");
            appPath = setProperties("appPath");
            hubHost = setProperties("hobHost");
            mitProxyPath = setProperties("mitProxyPath");
            isRunProxy = setProperties("isRunProxy");
        }
    }
}
