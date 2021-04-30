package utilities.verfications;

import base.Base;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Collections;
import static utilities.uiActions.UiActions.getString;

@Slf4j
public class Verifications extends Base {

    @Description("load")
    public void load(WebElement element) {
        load(new ArrayList<>(Collections.singleton(element)));
    }

    @Description("load")
    public void load(ArrayList<WebElement> elements) {
        for (WebElement pageEle : elements) {
            if (utilities.uiActions().elementPresented(pageEle, 5)) {
                log.info("load " + pageEle.toString());
            } else {
                log.info("fail load " + pageEle.toString());
            }
        }
    }

    @Description("verify number of elements")
    public void verifyNumberOfElements(ArrayList<WebElement> elements, int numbers) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver,10);
            webDriverWait.until(ExpectedConditions.visibilityOf(elements.get(elements.size() -1)));
            Assert.assertEquals(elements.size(), numbers);
        } catch (WebDriverException webDriverException) {
            log.debug(webDriverException.getMessage());
            Assert.fail(webDriverException.getMessage());
        }
    }

    @Description("compere texts")
    public boolean compereTexts(ArrayList<WebElement> elements, ArrayList<String> text) {
        if (elements.size() != text.size()) {
            Assert.fail(elements.size() + " not equals to " + text.size());
        }

        for (int i = 0; i < elements.size(); i++) {
            if (isTextEquals(elements.get(i), text.get(i))) {
                log.debug("actual text " + elements.get(i).getText() + " equals expected test " + text.get(i));
                return true;
            } else {
                log.debug("actual text " + elements.get(i).getText() + " not equals expected test " + text.get(i));
            }
        }

        return false;
    }

    @Description("is text equals")
    public boolean isTextEquals(WebElement actual, String expectedText) {
        load(actual);
        String setActual = getString(actual.getText()).orElse("empty").trim();
        String setExpected = getString(expectedText).orElse("empty").trim();
        return  setActual.equals(setExpected) ||
                setActual.contentEquals(setExpected) ||
                setActual.contains(setExpected);
    }

}
