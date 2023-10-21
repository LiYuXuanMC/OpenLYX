package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.IWrapper;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.TextureManager",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.texture.TextureManager",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class TextureManager extends IWrapper {
    @WrapMethod(mcpName = "bindTexture",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "bindTexture",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method bindTexture;
    @WrapMethod(mcpName = "loadTexture",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "loadTexture",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method loadTexture;
    @WrapMethod(mcpName = "getTexture",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getTexture",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getTexture;

    public TextureManager(Object obj) {
        super(obj);
    }
    public void bindTexture(ResourceLocation resource){
        try{
            invoke(bindTexture,resource.getWrappedObject());
        }catch (Throwable a){
            
        }

    }
    public void loadTexture(ResourceLocation resourceLocation, DynamicTexture dynamicTexture) {
        invoke(loadTexture,resourceLocation.getWrappedObject(),dynamicTexture.getWrappedObject());
    }
}
