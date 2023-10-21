package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.annotations.CactusWrapping;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.AbstractTexture",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.AbstractTexture",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class AbstractTexture extends IWrapper {
    @WrapMethod(mcpName = "getGlTextureId",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "()I")
    @WrapMethod(mcpName = "getGlTextureId",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @CactusWrapping
    public static Method getGlTextureId;
    public AbstractTexture(Object obj) {
        super(obj);
    }
    public int getGlTextureId()
    {
        return (int) invoke(getGlTextureId);
    }
}
