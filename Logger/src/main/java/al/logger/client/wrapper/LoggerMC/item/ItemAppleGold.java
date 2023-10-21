package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemAppleGold",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemAppleGold",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemAppleGold extends ItemFood{
@ClassInstance
public static Class ItemAppleGoldClass;
    public ItemAppleGold(Object obj) {
        super(obj);
    }
    public static boolean isItemAppleGold(Item item){
        return ItemAppleGoldClass.isInstance(item.getWrappedObject());
    }
}
