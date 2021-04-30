package test.flightPageRegistration;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import test.FactoryBaseTest;
import test.flightPageRegistration.components.RegistrationComponent;
import test.flightPageRegistration.components.RegistrationConfirmationComponent;
import utilities.UiUtilitiesObjects;

import java.util.function.BiConsumer;

@Slf4j
public class ShareComponents extends FactoryBaseTest {

    public ShareComponents shareComponents(String print, BiConsumer<Pair<UiUtilitiesObjects, FlightUi>,
                Pair<RegistrationComponent,RegistrationConfirmationComponent>> testAction) {
        log.info(print);
        testAction.accept(
                Pair.of(new UiUtilitiesObjects(), new FlightUi(driver)),
                Pair.of(new RegistrationComponent(), new RegistrationConfirmationComponent()));
        return new ShareComponents();
    }
}
