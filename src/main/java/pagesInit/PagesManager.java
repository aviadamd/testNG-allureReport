package pagesInit;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PagesManager {

    public PagesManager(PageType type, WebDriver driver) {
        switch (type) {
            case WEB:
                PageFactory.initElements(driver,this);
                break;
            case MOBILE:
                PageFactory.initElements(new AppiumFieldDecorator(driver),this);
                break;
            default: throw new IllegalArgumentException(type + " is not valid arg");
        }
    }
}
