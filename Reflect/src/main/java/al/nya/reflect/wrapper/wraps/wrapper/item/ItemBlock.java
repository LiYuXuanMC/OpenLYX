package al.nya.reflect.wrapper.wraps.wrapper.item;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.block.Block;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.world.World;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.item.ItemBlock", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.item.ItemBlock", targetMap = Maps.Srg1_12_2)
public class ItemBlock extends Item {
    @WrapClass(mcpName = "net.minecraft.item.ItemBlock", targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.item.ItemBlock", targetMap = Maps.Srg1_12_2)
    public static Class ItemBlockClass;
    @WrapMethod(mcpName = "getBlock", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "getBlock", targetMap = Maps.Srg1_12_2)
    public static Method getBlock;
    @WrapMethod(mcpName = "canPlaceBlockOnSide", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "canPlaceBlockOnSide", targetMap = Maps.Srg1_12_2)
    public static Method canPlaceBlockOnSide;
    @WrapMethod(mcpName = "getItemFromBlock", targetMap = Maps.Srg1_8_9)
    public static Method getItemFromBlock;

    public ItemBlock(Object obj) {
        super(obj);
    }

    public static boolean isItemBlock(Item item) {
        return ItemBlockClass.isInstance(item.getWrapObject());
    }

    public Block getBlock() {
        return new Block(invoke(getBlock));
    }

    public boolean canPlaceBlockOnSide(World world, BlockPos blockPos, Enum<?> sideHit, EntityPlayer entityPlayer, ItemStack itemstack) {
        return (boolean) invoke(canPlaceBlockOnSide, world.getWrapObject(), blockPos.getWrapObject(), sideHit, entityPlayer.getWrapObject(), itemstack.getWrapObject());
    }

    public static Item getItemFromBlock(Block block) {
        return new Item(ReflectUtil.invoke(getItemFromBlock, null, block.getWrapObject()));
    }
}
