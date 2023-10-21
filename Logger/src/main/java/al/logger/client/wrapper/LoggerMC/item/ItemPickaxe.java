package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.item.ItemPickaxe",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemPickaxe",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemPickaxe extends ItemTool{
@ClassInstance
public static Class ItemPickaxeClass;
    public ItemPickaxe(Object obj) {
        super(obj);
    }
    public static boolean isItemPickaxe(Item item){
        return ItemPickaxeClass.isInstance(item.getWrappedObject());
    }
}
