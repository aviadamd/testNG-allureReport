package listeners;

import base.Base;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.testng.AllureTestNg;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class AllureListener extends Base implements ITestListener {

    private static final AllureTestNg allureTestNg = new AllureTestNg();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        allureTestNg.onTestStart(iTestResult);
        log.debug("test start method " + getTestMethodName(iTestResult) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        allureTestNg.onTestSuccess(iTestResult);
        log.debug("test success method " + getTestMethodName(iTestResult) + " succeed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.debug("fail " + result.getName());
        saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");
        saveScreenshotPNG(driver);
        allureTestNg.onTestFailure(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.debug("skip " + result.getName());
        allureTestNg.onTestFailure(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.debug("fail with steps success " + result.getName());
        allureTestNg.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onStart(ITestContext context) {
        log.debug("start " + context.getName());
        allureTestNg.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        log.debug("finish tests " + context.getName());
        allureTestNg.onFinish(context);
    }

    // Image attachment for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {

        return html;
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
}
