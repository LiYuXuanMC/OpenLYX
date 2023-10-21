package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.entity.projectile.EntityArrow", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.projectile.EntityArrow", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityArrow extends Entity {
@ClassInstance    
public static Class EntityArrowClass;
    @WrapField(mcpName = "inGround", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "inGround", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field inGround;

    public EntityArrow(Object obj) {
        super(obj);
    }

    public static boolean isEntityArrow(Entity obj) {
        return EntityArrowClass.isInstance(obj.getWrappedObject());
    }

    public boolean isGround() {
        return (boolean) getField(inGround);
    }
}

