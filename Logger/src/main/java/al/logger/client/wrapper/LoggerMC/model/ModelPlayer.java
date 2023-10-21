package al.logger.client.wrapper.LoggerMC.model;


import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.model.ModelPlayer", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.model.ModelPlayer", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
public class ModelPlayer extends ModelBiped{
    @ClassInstance
    public static Class<?> ModelPlayerClass;

    @WrapMethod(mcpName = "renderCape",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "renderCape",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method renderCape;
    public ModelPlayer(Object obj) {
        super(obj);
    }

    public void renderCape(float fv) {
        invoke(renderCape,fv);
    }

}
