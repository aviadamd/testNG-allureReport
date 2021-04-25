package base;

import org.testng.ITestResult;

public interface ScreenshotConsumer {
    void handle(byte [] screenshot, ITestResult testResult);
}
