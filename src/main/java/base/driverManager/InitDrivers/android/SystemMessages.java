package base.driverManager.InitDrivers.android;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SystemMessages extends Base {

    public boolean isGetSystemMessage(List<WebElement> popUp, WebElement approve) {
        return utilities.uiActions().webDriverWait(
                2,
                ExpectedConditions.visibilityOfAllElements(popUp),
                approve
        );
    }

    public List<WebElement> alertActivity() {
        final String app = "android:id/";
        return new ArrayList<>(Arrays.asList(
                driver.findElement(By.id(app + "parentPanel")),
                driver.findElement(By.id(app +"alertTitle")),
                driver.findElement(By.id(app + "message")),
                driver.findElement(By.id(app + "button1"))
        ));
    }

    public List<WebElement> permissionActivity() {
        final String app = "com.android.packageinstaller:id/";
        return new ArrayList<>(Arrays.asList(
                driver.findElement(By.id(app + "dialog_container")),
                driver.findElement(By.id(app + "permission_message")),
                driver.findElement(By.id(app + "permission_allow_button")),
                driver.findElement(By.id(app + "permission_deny_button"))
        ));
    }
}
