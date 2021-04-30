package utilities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum WaitCondition {
    visible(ExpectedConditions::visibilityOf),
    enabled(ExpectedConditions::elementToBeClickable);
    private final Function<WebElement, ExpectedCondition<WebElement>> type;

//        WaitCondition(Function<WebElement, ExpectedCondition<WebElement>> type) {
//        this.type = type;
//    }
//    public Function<WebElement, ExpectedCondition<WebElement>> getType() {
//        return type;
//    }
}
