package al.logger.client.wrapper.LoggerMC.render.entity;

import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.Render",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.Render",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class Render extends IWrapper {
    @ClassInstance
    public static Class<?> RenderClass;
    public Render(Object obj) {
        super(obj);
    }

    @WrapMethod(mcpName = "bindTexture", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "bindTexture", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method bindTexture;


    public void bindTexture(ResourceLocation resourceLocation){
        invoke(bindTexture,resourceLocation.getWrappedObject());
    }



}
