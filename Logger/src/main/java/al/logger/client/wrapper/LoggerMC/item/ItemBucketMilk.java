package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

@WrapperClass(mcpName = "net.minecraft.item.ItemBucketMilk", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemBucketMilk", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemBucketMilk extends Item {

    @ClassInstance
    public static Class ItemBucketMilkClass;

    public ItemBucketMilk(Object obj) {
        super(obj);
    }

    public static boolean isItemBucketMilk(Item item) {
        return ItemBucketMilkClass.isInstance(item.getWrappedObject());
    }
}
