package al.logger.client.wrapper.LoggerMC.world;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

//net.minecraft.world.WorldSettings
@WrapperClass(mcpName = "net.minecraft.world.WorldSettings",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class WorldSettings extends IWrapper {
    public WorldSettings(Object obj) {
        super(obj);
    }
}
