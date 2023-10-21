package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.entity.passive.EntityAnimal",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.entity.passive.EntityAnimal",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityAnimal extends EntityAgeable {
@ClassInstance    
public static Class EntityAnimalClass;
    public EntityAnimal(Object obj) {
        super(obj);
    }
    public static boolean isEntityAnimal(Entity o){
        return EntityAnimalClass.isInstance(o.getWrappedObject());
    }
}
