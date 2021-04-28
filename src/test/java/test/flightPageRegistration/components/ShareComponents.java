package test.flightPageRegistration.components;

import base.Action;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import test.FactoryBaseTest;
import test.flightPageRegistration.FlightUi;
import utilities.UiUtilitiesObjects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Slf4j
public class ShareComponents extends FactoryBaseTest {

    public RegistrationComponent registration(String print, Consumer<Triple<RegistrationComponent
            , UiUtilitiesObjects, FlightUi>> testAction) {
        log.info(print);
        testAction.accept(Triple.of(new RegistrationComponent(), new UiUtilitiesObjects(), new FlightUi(driver)));
        return new RegistrationComponent();
    }

    public RegistrationComponent registrations(String print, BiConsumer<Pair<RegistrationComponent, Action>,
            Pair<UiUtilitiesObjects, FlightUi>> testAction) {
        log.info(print);
        testAction.accept(Pair.of(new RegistrationComponent(), new Action()), Pair.of(new UiUtilitiesObjects(), new FlightUi(driver)));
        return new RegistrationComponent();
    }

    public RegistrationConfirmationComponent
    registrationConfirmationComponent(String print, Consumer<Triple<RegistrationConfirmationComponent,
            UiUtilitiesObjects, FlightUi>> testAction) {
        log.info(print);
        testAction.accept(Triple.of(new RegistrationConfirmationComponent(), new UiUtilitiesObjects(), new FlightUi(driver)));
        return new RegistrationConfirmationComponent();
    }

    public ShareComponents shareComponents(String print, Consumer<Triple<ShareComponents,
            UiUtilitiesObjects, FlightUi>> testAction) {
        log.info(print);
        testAction.accept(Triple.of(new ShareComponents(), new UiUtilitiesObjects(), new FlightUi(driver)));
        return new ShareComponents();
    }
}
