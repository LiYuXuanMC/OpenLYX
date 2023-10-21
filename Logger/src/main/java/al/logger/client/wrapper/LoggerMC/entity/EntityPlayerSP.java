package al.logger.client.wrapper.LoggerMC.entity;

import al.logger.client.wrapper.annotations.*;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.LoggerMC.network.NetHandlerPlayClient;
import al.logger.client.wrapper.LoggerMC.utils.MovementInput;
import net.minecraft.entity.player.EntityPlayer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.entity.EntityPlayerSP",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.entity.EntityPlayerSP",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class EntityPlayerSP extends AbstractClientPlayer {
    @WrapField(mcpName = "sendQueue",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "connection",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field sendQueue;

    @WrapField(mcpName = "movementInput",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "movementInput",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field movementInput;
    @ClassInstance
    public static Class EntityPlayerSPClass;
    @WrapMethod(mcpName = "setSprinting",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "setSprinting",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method setSprinting;
    @WrapMethod(mcpName = "onUpdateWalkingPlayer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "()V")
    @WrapMethod(mcpName = "onUpdateWalkingPlayer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping
    public static Method onUpdateWalkingPlayer;
    @WrapMethod(mcpName = "onUpdate",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "onUpdate",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method onUpdate;
    @WrapMethod(mcpName = "sendChatMessage",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "sendChatMessage",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method sendChatMessage;
    @WrapMethod(mcpName = "isSneaking",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "isSneaking",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method isSneaking;
    @WrapField(mcpName = "serverSprintState", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "serverSprintState", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field serverSprintState;
    @WrapMethod(mcpName = "closeScreen", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "closeScreen", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method closeScreen;
    @WrapMethod(mcpName = "onEnchantmentCritical", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "onEnchantmentCritical", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method onEnchantmentCritical;
    @WrapField(mcpName = "prevRenderArmPitch", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "prevRenderArmPitch", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field prevRenderArmPitch;
    @WrapField(mcpName = "renderArmPitch", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "renderArmPitch", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field renderArmPitch;
    @WrapMethod(mcpName = "pushOutOfBlocks", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method pushOutOfBlocks;

    public EntityPlayerSP(Object obj) {
        super(obj);
    }
    public void setFallDistance(int distance) {
        setField(fallDistance,distance);
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
        return new NetHandlerPlayClient(ReflectUtil.getField(sendQueue, getWrappedObject()));
    }

    public static boolean isEntityPlayerSP(Entity o) {
        return EntityPlayerSPClass.isInstance(o.getWrappedObject());
    }

    public MovementInput getMovementInput() {
        return new MovementInput(ReflectUtil.getField(movementInput, getWrappedObject()));
    }

    public void onEnchantmentCritical(Entity entity) {
        invoke(onEnchantmentCritical, entity.getWrappedObject());
    }

    public void setSprinting(boolean b) {
        ReflectUtil.invoke(setSprinting, getWrappedObject(), b);
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
    public float getDirection() {
        float yaw = getRotationYaw();
        if (getMoveForward() < 0)
            yaw += 180;
        float forward = 1;
        if (getMoveForward() < 0)
            forward = -.5F;
        else if (getMoveForward() > 0)
            forward = .5F;
        if (getMoveStrafing() > 0)
            yaw -= 90 * forward;
        if (getMoveStrafing() < 0)
            yaw += 90 * forward;
        yaw *= .017453292;
        return yaw;
    }


}
