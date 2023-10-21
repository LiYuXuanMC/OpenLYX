package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.monster.EntityMob",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.monster.EntityMob",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityMob extends EntityCreature {
@ClassInstance    
public static Class EntityMobClass;
    public EntityMob(Object obj) {
        super(obj);
    }
    public static boolean isEntityMob(Entity o){
        return EntityMobClass.isInstance(o.getWrappedObject());
    }
}
