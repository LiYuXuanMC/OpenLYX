package al.logger.client.features.modules;

import al.logger.client.value.impls.OptionValue;
import lombok.Getter;

public class GlobalConfiguration {
    @Getter
    private final OptionValue killauraAttackScaffold = new OptionValue("KillAura Attack When Scaffold", false);
    public GlobalConfiguration() {

    }
}
