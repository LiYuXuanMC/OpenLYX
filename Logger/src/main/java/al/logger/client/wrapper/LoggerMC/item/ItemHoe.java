package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemHoe",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemHoe",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemHoe extends Item{
@ClassInstance
public static Class ItemHoeClass;
    public ItemHoe(Object obj) {
        super(obj);
    }
    public static boolean isItemHoe(Item item){
        return ItemHoeClass.isInstance(item.getWrappedObject());
    }
}
