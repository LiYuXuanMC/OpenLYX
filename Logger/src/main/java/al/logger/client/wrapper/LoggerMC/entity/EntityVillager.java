package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.passive.EntityVillager",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.passive.EntityVillager",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityVillager extends EntityAgeable{
@ClassInstance    
public static Class EntityVillagerClass;
    public EntityVillager(Object obj) {
        super(obj);
    }
    public static boolean isEntityVillager(Entity o){
        return EntityVillagerClass.isInstance(o.getWrappedObject());
    }
}
