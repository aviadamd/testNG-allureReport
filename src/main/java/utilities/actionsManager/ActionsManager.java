package utilities.actionsManager;

import org.openqa.selenium.WebElement;
import utilities.UiUtilitiesObjects;

public abstract class ActionsManager extends UiUtilitiesObjects {
    public abstract void click(WebElement element);
    public abstract void clickOptional(WebElement element);
    public abstract void clear(WebElement element);
    public abstract void sendKeys(WebElement element, String keys);
}
