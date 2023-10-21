package al.nya.reflect.wrapper.wraps.wrapper.network;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapConstructor;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumHand;

import java.lang.reflect.Constructor;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C08PacketPlayerBlockPlacement",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock",targetMap = Maps.Srg1_12_2)
public class C08PacketPlayerBlockPlacement extends Packet{
    @WrapClass(mcpName = "net.minecraft.network.play.client.C08PacketPlayerBlockPlacement",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock",targetMap = Maps.Srg1_12_2)
    public static Class C08PacketPlayerBlockPlacementClass;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {ItemStack.class})
    public static Constructor C08PacketPlayerBlockPlacement_ItemStack;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {BlockPos.class,int.class,ItemStack.class,float.class,float.class,float.class})
    public static Constructor C08PacketPlayerBlockPlacement_BlockPos_I_ItemStack_FFF;
    @WrapConstructor(targetMap = Maps.Srg1_8_9,signature = {BlockPos.class, EnumFacing.class, EnumHand.class,float.class,float.class,float.class})
    public static Constructor C08PacketPlayerBlockPlacement_BlockPos_Facing_Hand_FFF;
    public C08PacketPlayerBlockPlacement(Object obj) {
        super(obj);
    }
    public C08PacketPlayerBlockPlacement(ItemStack itemStack){
        this(ReflectUtil.construction(C08PacketPlayerBlockPlacement_ItemStack,itemStack.getWrapObject()));
    }

    public C08PacketPlayerBlockPlacement(BlockPos positionIn, int placedBlockDirectionIn, ItemStack stackIn, float facingXIn, float facingYIn, float facingZIn) {
        super(ReflectUtil.construction(C08PacketPlayerBlockPlacement_BlockPos_I_ItemStack_FFF,
                positionIn.getWrapObject(), placedBlockDirectionIn, stackIn.getWrapObject(), facingXIn, facingYIn, facingZIn));
    }

    public C08PacketPlayerBlockPlacement(BlockPos positionIn, Enum facing, Enum hand, float facingXIn, float facingYIn, float facingZIn) {
        super(ReflectUtil.construction(C08PacketPlayerBlockPlacement_BlockPos_Facing_Hand_FFF,
                positionIn.getWrapObject(), facing, hand, facingXIn, facingYIn, facingZIn));
    }

    public static boolean isPacketPlayerBlockPlacement(Packet packet) {
        return C08PacketPlayerBlockPlacementClass.isInstance(packet.getWrapObject());
    }
}
