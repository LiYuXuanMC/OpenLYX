package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;

@WrapperClass(mcpName = "net.minecraft.item.ItemFishingRod",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemFishingRod",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemFishingRod extends Item{
@ClassInstance    
public static Class ItemFishingRodClass;
    public ItemFishingRod(Object obj) {
        super(obj);
    }
    public static boolean isItemFishingRod(Item item){
        return ItemFishingRodClass.isInstance(item.getWrappedObject());
    }
}
