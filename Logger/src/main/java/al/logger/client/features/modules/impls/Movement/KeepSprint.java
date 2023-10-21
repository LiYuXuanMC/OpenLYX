package al.logger.client.features.modules.impls.Movement;

import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Hazard;
import al.logger.client.features.modules.Module;

public class KeepSprint extends Module {
    public KeepSprint() {
        super("KeepSprint",Category.Movement);
        this.setHazard(Hazard.HIGH);
        this.setDescription("Does not cancel sprint when attacking");
    }
}
