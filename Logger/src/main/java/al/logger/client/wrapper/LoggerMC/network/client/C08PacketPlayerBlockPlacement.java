package al.logger.client.wrapper.LoggerMC.network.client;

import al.logger.client.wrapper.LoggerMC.network.Packet;
import al.logger.client.wrapper.annotations.*;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.EnumFacing;
import al.logger.client.wrapper.LoggerMC.utils.EnumHand;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.network.play.client.C08PacketPlayerBlockPlacement",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class C08PacketPlayerBlockPlacement extends Packet {
@ClassInstance    
public static Class C08PacketPlayerBlockPlacementClass;
    @WrapField(mcpName = "stack",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    private Field stack;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {ItemStack.class})
    public static Constructor C08PacketPlayerBlockPlacement_ItemStack;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = {BlockPos.class,int.class,ItemStack.class,float.class,float.class,float.class})
    public static Constructor C08PacketPlayerBlockPlacement_BlockPos_I_ItemStack_FFF;
    @WrapConstructor(targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = {BlockPos.class, EnumFacing.class, EnumHand.class,float.class,float.class,float.class})
    public static Constructor C08PacketPlayerBlockPlacement_BlockPos_Facing_Hand_FFF;

    @WrapMethod(mcpName = "getPosition",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method getPosition;
    public C08PacketPlayerBlockPlacement(Object obj) {
        super(obj);
    }
    public C08PacketPlayerBlockPlacement(ItemStack itemStack){
        this(ReflectUtil.construction(C08PacketPlayerBlockPlacement_ItemStack,itemStack.getWrappedObject()));
    }

    public C08PacketPlayerBlockPlacement(BlockPos positionIn, int placedBlockDirectionIn, ItemStack stackIn, float facingXIn, float facingYIn, float facingZIn) {
        super(ReflectUtil.construction(C08PacketPlayerBlockPlacement_BlockPos_I_ItemStack_FFF,
                positionIn.getWrappedObject(), placedBlockDirectionIn, stackIn.getWrappedObject(), facingXIn, facingYIn, facingZIn));
    }

    public C08PacketPlayerBlockPlacement(BlockPos positionIn, Enum facing, Enum hand, float facingXIn, float facingYIn, float facingZIn) {
        super(ReflectUtil.construction(C08PacketPlayerBlockPlacement_BlockPos_Facing_Hand_FFF,
                positionIn.getWrappedObject(), facing, hand, facingXIn, facingYIn, facingZIn));
    }

    public static boolean isPacketPlayerBlockPlacement(Packet packet) {
        return C08PacketPlayerBlockPlacementClass.isInstance(packet.getWrappedObject());
    }

    public Object getStack() {
        return new ItemStack(ReflectUtil.getField(stack, getWrappedObject())).getWrappedObject();
    }

    public BlockPos getPosition() {
        return new BlockPos(ReflectUtil.invoke(getPosition, getWrappedObject()));
    }
}
