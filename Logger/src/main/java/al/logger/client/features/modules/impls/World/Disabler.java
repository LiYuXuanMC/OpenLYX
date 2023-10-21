package al.logger.client.features.modules.impls.World;

import al.logger.client.Logger;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.ModuleCarrier;
import al.logger.client.features.modules.impls.World.disablers.Matrix.MatrixDisabler;
import al.logger.client.features.modules.impls.World.disablers.Vulcan.VulcanDisabler;

public class Disabler extends ModuleCarrier {

    public Disabler(){
        super("Disabler", Category.World);
        if (Logger.getInstance().getLoggerUser().getPower() == 10 || Logger.getInstance().getLoggerUser().getPower() == 255) {
            // TODO: 2023/8/10 这个Disabler模块的子模块会导致ReflectUtil爆炸
            addSubModule(new MatrixDisabler());
        }
        addSubModule(new VulcanDisabler());
    }

}