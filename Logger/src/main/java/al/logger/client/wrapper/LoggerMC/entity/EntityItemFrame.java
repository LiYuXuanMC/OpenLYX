package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.item.EntityItemFrame", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.item.EntityItemFrame", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityItemFrame extends Entity {
@ClassInstance    
public static Class EntityItemFrameClass;
    public EntityItemFrame(Object obj) {
        super(obj);
    }
    public static boolean isEntityItemFrame(Entity entity){
        return EntityItemFrameClass.isInstance(entity.getWrappedObject());
    }
}
