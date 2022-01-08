package test.accountManagement.component;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import test.FactoryBaseTest;
import test.accountManagement.pages.LoginPage;
import utilities.UiUtilitiesObjects;

import java.util.function.Consumer;

public class AccountManagementSharedComponents extends FactoryBaseTest {

    public AccountManagementSharedComponents sharedComponents(Consumer<Pair<UiUtilitiesObjects, LoginPage>> actions) {
        actions.accept(Pair.of(new UiUtilitiesObjects(), new LoginPage(driver)));
        return new AccountManagementSharedComponents();
    }

    public void action(Consumer<Pair<UiUtilitiesObjects, LoginPage>> actions) {
        try {
            actions.accept(Pair.of(new UiUtilitiesObjects(), new LoginPage(driver)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
