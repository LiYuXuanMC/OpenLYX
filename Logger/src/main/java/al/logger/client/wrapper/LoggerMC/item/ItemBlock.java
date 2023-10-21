package al.logger.client.wrapper.LoggerMC.item;

import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.LoggerMC.block.Block;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.world.World;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.item.ItemBlock", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.item.ItemBlock", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemBlock extends Item {
    @ClassInstance
    public static Class ItemBlockClass;
    @WrapMethod(mcpName = "getBlock", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getBlock", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getBlock;
    @WrapMethod(mcpName = "canPlaceBlockOnSide", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "canPlaceBlockOnSide", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method canPlaceBlockOnSide;
    @WrapField(mcpName = "block", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Field block;

    public ItemBlock(Object obj) {
        super(obj);
    }

    public static boolean isItemBlock(Item item) {
        return ItemBlockClass.isInstance(item.getWrappedObject());
    }

    public Block getBlock() {
        return new Block(getField(block));
    }

    public boolean canPlaceBlockOnSide(World world, BlockPos blockPos, Enum<?> sideHit, EntityPlayer entityPlayer, ItemStack itemstack) {
        return (boolean) invoke(canPlaceBlockOnSide, world.getWrappedObject(), blockPos.getWrappedObject(), sideHit, entityPlayer.getWrappedObject(), itemstack.getWrappedObject());
    }

}
