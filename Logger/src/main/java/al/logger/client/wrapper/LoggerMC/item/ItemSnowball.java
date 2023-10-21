package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemSnowball",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemSnowball",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemSnowball extends Item{
@ClassInstance    
public static Class ItemSnowballClass;
    public ItemSnowball(Object obj) {
        super(obj);
    }
    public static boolean isItemSnowball(Item item){
        return ItemSnowballClass.isInstance(item.getWrappedObject());
    }
}
