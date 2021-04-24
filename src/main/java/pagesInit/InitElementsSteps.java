package pagesInit;

import org.openqa.selenium.WebDriver;

public interface InitElementsSteps {
    void initElements(WebDriver driver, String description);
    boolean isAndroidDriver(WebDriver driver);
    boolean isWebDriver(WebDriver driver);
}
