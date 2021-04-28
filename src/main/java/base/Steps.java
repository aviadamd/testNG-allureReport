package base;

import org.apache.commons.lang3.tuple.Pair;
import utilities.UiUtilitiesObjects;
import java.util.function.Consumer;

public interface Steps {
    void goTo(String baseUrl);
    void step(String name, Consumer<Pair<Action, UiUtilitiesObjects>> action);
}
