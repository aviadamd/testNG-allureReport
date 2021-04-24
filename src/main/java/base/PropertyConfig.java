package base;

import lombok.SneakyThrows;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyConfig {

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
        FileInputStream fileInputStream =
                new FileInputStream(System.getProperty("user.dir") + path);
        properties.load(fileInputStream);

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
        fileInputStream.close();
    }

    private static String setProperties(String proName) {
        if (System.getProperty(proName) != null)
             return System.getProperty(proName);
        else return properties.getProperty(proName);
    }
}
