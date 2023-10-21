package al.logger.client.features.modules.impls.Movement;

import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Hazard;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;

public class Strafe extends Module {

    OptionValue ondamge = new OptionValue("OnDamage",false);

    DoubleValue hurttime = new DoubleValue("HurtTime",5,1,3,0.0);
    public Strafe() {
        super("Strafe",Category.Movement);
        setDescription("Strafe in the Air");
        setHazard(Hazard.HACK);
    }

}
