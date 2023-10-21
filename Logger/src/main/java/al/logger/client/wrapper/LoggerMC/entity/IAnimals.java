package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.entity.passive.IAnimals", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.passive.IAnimals", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class IAnimals extends IWrapper {
@ClassInstance
public static Class IAnimalsClass;
    public IAnimals(Object obj) {
        super(obj);
    }
    public static boolean isIAnimals(Entity o){
        return IAnimalsClass.isInstance(o.getWrappedObject());
    }
}
