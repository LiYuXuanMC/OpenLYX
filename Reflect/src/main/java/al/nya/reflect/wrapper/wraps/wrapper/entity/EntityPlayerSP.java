package al.nya.reflect.wrapper.wraps.wrapper.entity;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.utils.ReflectUtil;
import al.nya.reflect.wrapper.wraps.annotation.WrapClass;
import al.nya.reflect.wrapper.wraps.annotation.WrapField;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovementInput;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.entity.EntityPlayerSP",targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.entity.EntityPlayerSP",targetMap = Maps.Srg1_12_2)
public class EntityPlayerSP extends AbstractClientPlayer {
    @WrapField(mcpName = "sendQueue",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "connection",targetMap = Maps.Srg1_12_2)
    public static Field sendQueue;
    @WrapField(mcpName = "movementInput",targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "movementInput",targetMap = Maps.Srg1_12_2)
    public static Field movementInput;
    @WrapClass(mcpName = "net.minecraft.client.entity.EntityPlayerSP",targetMap = Maps.Srg1_8_9)
    @WrapClass(mcpName = "net.minecraft.client.entity.EntityPlayerSP",targetMap = Maps.Srg1_12_2)
    public static Class EntityPlayerSPClass;
    @WrapMethod(mcpName = "setSprinting",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "setSprinting",targetMap = Maps.Srg1_12_2)
    public static Method setSprinting;
    @WrapMethod(mcpName = "onUpdateWalkingPlayer",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "onUpdateWalkingPlayer",targetMap = Maps.Srg1_12_2)
    public static Method onUpdateWalkingPlayer;
    @WrapMethod(mcpName = "onUpdate",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "onUpdate",targetMap = Maps.Srg1_12_2)
    public static Method onUpdate;
    @WrapMethod(mcpName = "sendChatMessage",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "sendChatMessage",targetMap = Maps.Srg1_12_2)
    public static Method sendChatMessage;
    @WrapMethod(mcpName = "isSneaking",targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "isSneaking",targetMap = Maps.Srg1_12_2)
    public static Method isSneaking;
    @WrapField(mcpName = "serverSprintState", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "serverSprintState", targetMap = Maps.Srg1_12_2)
    public static Field serverSprintState;
    @WrapMethod(mcpName = "closeScreen", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "closeScreen", targetMap = Maps.Srg1_12_2)
    public static Method closeScreen;
    @WrapMethod(mcpName = "onEnchantmentCritical", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "onEnchantmentCritical", targetMap = Maps.Srg1_12_2)
    public static Method onEnchantmentCritical;
    @WrapField(mcpName = "prevRenderArmPitch", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "prevRenderArmPitch", targetMap = Maps.Srg1_12_2)
    public static Field prevRenderArmPitch;
    @WrapField(mcpName = "renderArmPitch", targetMap = Maps.Srg1_8_9)
    @WrapField(mcpName = "renderArmPitch", targetMap = Maps.Srg1_12_2)
    public static Field renderArmPitch;
    @WrapMethod(mcpName = "pushOutOfBlocks", targetMap = Maps.Srg1_8_9)
    public static Method pushOutOfBlocks;

    public EntityPlayerSP(Object obj) {
        super(obj);
    }

    public float getPrevRenderArmPitch() {
        return (float) getField(prevRenderArmPitch);
    }

    public void setPrevRenderArmPitch(float value) {
        setField(prevRenderArmPitch, value);
    }

    public float getRenderArmPitch() {
        return (float) getField(renderArmPitch);
    }

    public void setRenderArmPitch(float value) {
        setField(renderArmPitch, value);
    }

    public NetHandlerPlayClient getSendQueue() {
        return new NetHandlerPlayClient(ReflectUtil.getField(sendQueue, getWrapObject()));
    }

    public static boolean isEntityPlayerSP(Entity o) {
        return EntityPlayerSPClass.isInstance(o.getWrapObject());
    }

    public MovementInput getMovementInput() {
        return new MovementInput(ReflectUtil.getField(movementInput, getWrapObject()));
    }

    public void onEnchantmentCritical(Entity entity) {
        invoke(onEnchantmentCritical, entity.getWrapObject());
    }

    public void setSprinting(boolean b) {
        ReflectUtil.invoke(setSprinting, getWrapObject(), b);
    }

    public void setServerSprintState(boolean b) {
        setField(serverSprintState, b);
    }

    public boolean getServerSprintState() {
        return (boolean) getField(serverSprintState);
    }

    public void closeScreen() {
        invoke(closeScreen);
    }

    public void sendChatMessage(String s) {
        invoke(sendChatMessage, s);
    }

    public boolean isSneaking() {
        return (boolean) invoke(isSneaking);
    }
}
