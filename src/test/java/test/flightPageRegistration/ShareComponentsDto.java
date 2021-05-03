package test.flightPageRegistration;

import org.apache.commons.lang3.tuple.Pair;
import test.flightPageRegistration.components.RegistrationComponent;
import test.flightPageRegistration.components.RegistrationConfirmationComponent;
import utilities.UiUtilitiesObjects;

import java.util.function.BiConsumer;

public interface ShareComponentsDto {
    void shareComponents(
            BiConsumer<Pair<UiUtilitiesObjects, FlightUi>,
                       Pair<RegistrationComponent, RegistrationConfirmationComponent>> testAction);
}
