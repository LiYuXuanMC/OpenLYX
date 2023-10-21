package com.reflectmc.reflect.wrapper.wrappers.minecraft.entity;

import com.reflectmc.reflect.wrapper.annotation.ClassInstance;
import com.reflectmc.reflect.wrapper.annotation.WrapField;
import com.reflectmc.reflect.wrapper.annotation.WrapMethod;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.NetHandlerPlayClient;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.MovementInput;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(deobfName = "net.minecraft.client.entity.EntityPlayerSP",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.client.entity.EntityPlayerSP",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class EntityPlayerSP extends AbstractClientPlayer {
    @WrapField(deobfName = "sendQueue",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "connection",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field sendQueue;
    @WrapField(deobfName = "movementInput",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "movementInput",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field movementInput;
    @ClassInstance
    public static Class EntityPlayerSPClass;
    @WrapMethod(deobfName = "setSprinting",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "setSprinting",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method setSprinting;
    @WrapMethod(deobfName = "onUpdateWalkingPlayer",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "onUpdateWalkingPlayer",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method onUpdateWalkingPlayer;
    @WrapMethod(deobfName = "onUpdate",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "onUpdate",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method onUpdate;
    @WrapMethod(deobfName = "sendChatMessage",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "sendChatMessage",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method sendChatMessage;
    @WrapMethod(deobfName = "isSneaking",targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "isSneaking",targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method isSneaking;
    @WrapField(deobfName = "serverSprintState", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "serverSprintState", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field serverSprintState;
    @WrapMethod(deobfName = "closeScreen", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "closeScreen", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method closeScreen;
    @WrapMethod(deobfName = "onEnchantmentCritical", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapMethod(deobfName = "onEnchantmentCritical", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Method onEnchantmentCritical;
    @WrapField(deobfName = "prevRenderArmPitch", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "prevRenderArmPitch", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field prevRenderArmPitch;
    @WrapField(deobfName = "renderArmPitch", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
    @WrapField(deobfName = "renderArmPitch", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
    public static Field renderArmPitch;
    @WrapMethod(deobfName = "pushOutOfBlocks", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
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
        return new NetHandlerPlayClient(getField(sendQueue));
    }
    public static boolean isEntityPlayerSP(Entity o) {
        return EntityPlayerSPClass.isInstance(o.getWrappedObject());
    }
    public MovementInput getMovementInput() {
        return new MovementInput(getField(movementInput));
    }
    public void onEnchantmentCritical(Entity entity) {
        invokeMethod(onEnchantmentCritical, entity.getWrappedObject());
    }
    public void setSprinting(boolean b) {
        invokeMethod(setSprinting, b);
    }
    public void setServerSprintState(boolean b) {
        setField(serverSprintState, b);
    }
    public boolean getServerSprintState() {
        return (boolean) getField(serverSprintState);
    }
    public void closeScreen() {
        invokeMethod(closeScreen);
    }
    public void sendChatMessage(String s) {
        invokeMethod(sendChatMessage, s);
    }
    public boolean isSneaking() {
        return (boolean) invokeMethod(isSneaking);
    }
    public void swingItem() {
        invokeMethod(swingItem);
    }
}
