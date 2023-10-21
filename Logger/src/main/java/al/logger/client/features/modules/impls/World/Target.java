package al.logger.client.features.modules.impls.World;

import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.OptionValue;

public class Target extends Module {
    public static OptionValue mobs = new OptionValue("Mobs", true);
    public static OptionValue invs = new OptionValue("Invisible", true);
    public static OptionValue players = new OptionValue("Players", true);
    public static OptionValue animals = new OptionValue("Animals", true);
    public Target() {
        super("Target", Category.World);
        addValues(mobs, invs, players, animals);
        this.setHide(true);
    }


}
