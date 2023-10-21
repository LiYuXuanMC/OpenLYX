package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemSpade",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemSpade",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemSpade extends ItemTool{
@ClassInstance    
public static Class ItemSpadeClass;
    public ItemSpade(Object obj) {
        super(obj);
    }
    public static boolean isItemSpade(Item item){
        return ItemSpadeClass.isInstance(item.getWrappedObject());
    }
}
