package al.logger.client.wrapper.LoggerMC.utils;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Field;

@WrapperClass(mcpName = "net.minecraft.util.MovementInput",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.util.MovementInput",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class MovementInput extends IWrapper {
    @WrapField(mcpName = "moveStrafe",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "moveStrafe",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field moveStrafeWrapper;
    @WrapField(mcpName = "moveForward",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "moveForward",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field moveForwardWrapper;
    @WrapField(mcpName = "jump",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "jump",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field jumpWrapper;
    @WrapField(mcpName = "sneak",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "sneak",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field sneakWrapper;

    public MovementInput(Object obj) {
        super(obj);
    }

    public float getMoveForward() {
        return (float) getField(moveForwardWrapper);
    }

    public float getMoveStrafe() {
        return (float) getField(moveStrafeWrapper);
    }

    public boolean isJump() {
        return (boolean) getField(jumpWrapper);
    }

    public boolean isSneak() {
        return (boolean) getField(sneakWrapper);
    }

    public void setMoveForward(float moveForward) {
        setField(moveForwardWrapper,moveForward);
    }

    public void setJump(boolean jump) {
        setField(jumpWrapper,jump);
    }

    public void setMoveStrafe(float moveStrafe) {
        setField(moveStrafeWrapper,moveStrafe);
    }

    public void setSneak(boolean sneak) {
        setField(sneakWrapper,sneak);
    }
}
