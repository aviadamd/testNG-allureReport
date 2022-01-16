package testBase;

import base.BaseMobile;
import org.testng.annotations.BeforeClass;

public class TestBase extends BaseMobile {

    public static TestBaseConfig testBaseConfig;

    @BeforeClass
    public void initClass() {
        String path = "/src/main/resources/config.properties";
        testBaseConfig = new TestBaseConfig(path);
    }
}
