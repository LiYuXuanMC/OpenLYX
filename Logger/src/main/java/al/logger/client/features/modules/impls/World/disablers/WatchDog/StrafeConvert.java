package al.logger.client.features.modules.impls.World.disablers.WatchDog;

import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.OptionValue;

public class StrafeConvert extends Module {
    OptionValue strafe = new OptionValue("Strafe" , false);

    public StrafeConvert() {
        super("WatchDog", Category.World);
        addValues(strafe);
    }




}
