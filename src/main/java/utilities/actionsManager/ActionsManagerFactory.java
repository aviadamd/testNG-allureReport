package utilities.actionsManager;

import base.Base;
import utilities.actionsManager.mobile.MobileUtils;
import utilities.actionsManager.web.WebUtils;

public class ActionsManagerFactory extends Base {

    public static ActionsManager getActionsManager(String platform) {
        ActionsManager actionsManager;
        switch (platform) {
            case "mobile":
                actionsManager = new MobileUtils();
                break;
            case "web":
                actionsManager = new WebUtils();
                break;
            default: throw new IllegalArgumentException(platform +  " is not legal argument");
        }
        return actionsManager;
    }
}
