package pagesInit;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class InitsPagesManager {

    public InitsPagesManager(PageType type, WebDriver driver) {
        switch (type) {
            case WEB:
                PageFactory.initElements(driver,this);
                break;
            case MOBILE:
                PageFactory.initElements(new AppiumFieldDecorator(driver),this);
                break;
            default:
                throw new IllegalArgumentException(type + " is not valid arg");
        }
    }
}
