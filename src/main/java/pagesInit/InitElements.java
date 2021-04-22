package pagesInit;

import org.openqa.selenium.WebDriver;

public interface InitElements {
    void initElements(WebDriver driver);
    boolean isAndroidDriver(WebDriver driver);
    boolean isWebDriver(WebDriver driver);
}
