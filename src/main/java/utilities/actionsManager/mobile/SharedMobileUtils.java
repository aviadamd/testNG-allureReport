package utilities.actionsManager.mobile;

import org.openqa.selenium.logging.LogEntry;
import utilities.UiUtilitiesObjects;

import java.util.ArrayList;
import java.util.List;

public class SharedMobileUtils extends UiUtilitiesObjects {

    public void printService() {
        for (String serviceOutPut : httpMessages()) {
            if (serviceOutPut.contains("ASSETS STORAGE CACHE")) {
                serviceOutPut = serviceOutPut.replace("ASSETS STORAGE CACHE","");
            }
            System.out.println(serviceOutPut);
        }
    }

    private boolean isHttp(String input) {
        return input.contains("OkHttp");
    }

    private boolean isOk(String input) {
        return input.contains("200");
    }

    private List<String> httpMessages() {
        List<String> logs = new ArrayList<>();
        List<LogEntry> logEntries = driver.manage().logs().get("logcat").getAll();
        for (LogEntry entry : logEntries) {
            logs.add(entry.getMessage());
        }
        return logs;
    }
}
