package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemEnderPearl",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemEnderPearl",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemEnderPearl extends Item{
@ClassInstance    
public static Class ItemEnderPearlClass;
    public ItemEnderPearl(Object obj) {
        super(obj);
    }
    public static boolean isItemEnderPearl(Item item){
        return ItemEnderPearlClass.isInstance(item.getWrappedObject());
    }
}
