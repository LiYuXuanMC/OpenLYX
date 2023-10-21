package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.item.EntityBoat",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.item.EntityBoat",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityBoat extends Entity {
@ClassInstance    
public static Class<?> EntityBoatClass;
    public EntityBoat(Object entity) {
        super(entity);
    }

    public static boolean isEntityBoat(Entity entity) {
        return EntityBoatClass.isInstance(entity.getWrappedObject());
    }

}
