package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;
@WrapperClass(mcpName = "net.minecraft.entity.item.EntityArmorStand",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.item.EntityArmorStand",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityArmorStand extends EntityLivingBase {
@ClassInstance
public static Class EntityArmorStandClass;
    public EntityArmorStand(Object obj) {
        super(obj);
    }
    public static boolean isEntityArmorStand(Entity entity){
        return EntityArmorStandClass.isInstance(entity.getWrappedObject());
    }
}
