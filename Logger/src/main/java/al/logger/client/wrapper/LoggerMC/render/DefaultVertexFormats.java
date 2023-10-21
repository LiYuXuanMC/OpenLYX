package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapField;
import al.logger.client.wrapper.annotations.WrapObject;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Field;


@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.DefaultVertexFormats", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.vertex.DefaultVertexFormats", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class DefaultVertexFormats extends IWrapper {
    @WrapObject(mcpName = "POSITION_TEX_COLOR", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapObject(mcpName = "POSITION_TEX_COLOR", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static VertexFormat POSITION_TEX_COLOR;
    @WrapObject(mcpName = "POSITION", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapObject(mcpName = "POSITION", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static VertexFormat POSITION;
    @WrapObject(mcpName = "POSITION_TEX", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapObject(mcpName = "POSITION_TEX", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static VertexFormat POSITION_TEX;
    @WrapObject(mcpName = "POSITION_COLOR", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapObject(mcpName = "POSITION_COLOR", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static VertexFormat POSITION_COLOR;

    public DefaultVertexFormats(Object obj) {
        super(obj);
    }
}
