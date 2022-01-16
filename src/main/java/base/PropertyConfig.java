package base;

import lombok.SneakyThrows;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyConfig {

    public String platform;
    public String platformType;
    public String remotePlatformType;
    public String url;
    public String localBin;
    public String androidSdk;
    public String nodeJs;
    public String appPath;
    public String hubHost;
    public String isRunProxy;
    public String mitProxyPath;
    private static Properties properties;

    @SneakyThrows
    public PropertyConfig(String path) {
        properties = new Properties();
        final String pathStr = System.getProperty("user.dir") + path;

        try (FileInputStream fileInputStream = new FileInputStream(pathStr)) {
            properties.load(fileInputStream);

            platform = setProperties("platform");
            platformType = setProperties("platformType");
            remotePlatformType = setProperties("remotePlatformType");
            url = setProperties("url");
            localBin = setProperties("localBin");
            androidSdk = setProperties("androidSdk");
            nodeJs = setProperties("nodeJs");
            appPath = setProperties("appPath");
            hubHost = setProperties("hobHost");
            mitProxyPath = setProperties("mitProxyPath");
            isRunProxy = setProperties("isRunProxy");
        }
    }

    public static String setProperties(String proName) {
        if (System.getProperty(proName) != null) {
            return System.getProperty(proName);
        } else if (properties.getProperty(proName) != null) {
            return properties.getProperty(proName);
        }
        throw new IllegalArgumentException(""+proName+" not exits in properties");
    }
}
