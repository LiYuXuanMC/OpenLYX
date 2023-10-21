package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.utils.EnumHand;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.ItemRenderer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.ItemRenderer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class ItemRenderer extends IWrapper {
    @ClassInstance
    public static Class<?> ItemRendererClass;
    @WrapMethod(mcpName = "resetEquippedProgress", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "resetEquippedProgress", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    private static Method resetEquippedProgress;
    @WrapMethod(mcpName = "renderItemInFirstPerson", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method renderItemInFirstPerson;
    @WrapMethod(mcpName = "transformFirstPersonItem", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method transformFirstPersonItem;
    @WrapField(mcpName = "equippedProgress", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    private static Field equippedProgress;
    @WrapField(mcpName = "prevEquippedProgress", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    private static Field prevEquippedProgress;

    public ItemRenderer(Object obj) {
        super(obj);
    }

    public void resetEquippedProgress() {
        invoke(resetEquippedProgress);
    }

    public void resetEquippedProgress(EnumHand enumHand) {
        invoke(resetEquippedProgress, enumHand);
    }

    public float getPrevEquippedProgress(){
        return (float) getField(prevEquippedProgress);
    }

    public float getEquippedProgress(){
        return (float) getField(equippedProgress);
    }

}
