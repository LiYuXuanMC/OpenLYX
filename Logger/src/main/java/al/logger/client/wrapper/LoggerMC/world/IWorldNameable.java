package al.logger.client.wrapper.LoggerMC.world;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.utils.text.IChatComponent;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.world.IWorldNameable",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.world.IWorldNameable",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class IWorldNameable extends IWrapper {
    @WrapMethod(mcpName = "getDisplayName",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getDisplayName",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getDisplayName;
    public IWorldNameable(Object obj) {
        super(obj);
    }
    public IChatComponent getDisplayName(){
        return new IChatComponent(invoke(getDisplayName));
    }
}
