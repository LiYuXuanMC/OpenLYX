package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.item.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.RenderItem", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.RenderItem", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class RenderItem extends IWrapper {
    @WrapField(mcpName = "zLevel", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapField(mcpName = "zLevel", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Field zLevel;
    @WrapMethod(mcpName = "renderItemIntoGUI", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "renderItemIntoGUI", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method renderItemIntoGUI;
    @WrapMethod(mcpName = "renderItemOverlayIntoGUI", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge, Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "renderItemOverlayIntoGUI", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge, Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method renderItemOverlayIntoGUI;

    public RenderItem(Object obj) {
        super(obj);
    }

    public float getZLevel() {
        return (float) getField(zLevel);
    }

    public void setZLevel(float f) {
        setField(zLevel, f);
    }

    public void renderItemIntoGUI(ItemStack stack, int x, int y) {
        invoke(renderItemIntoGUI, stack.getWrappedObject(), x, y);
    }

    public void renderItemOverlayIntoGUI(FontRenderer fr, ItemStack stack, int xPosition, int yPosition, String text) {
        invoke(renderItemOverlayIntoGUI, fr.getWrappedObject(), stack.getWrappedObject(), xPosition, yPosition, text);
    }
}
