package al.logger.client.wrapper.LoggerMC.render.entity;

import al.logger.client.wrapper.LoggerMC.model.ModelPlayer;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.environment.Environment;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.RenderPlayer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.entity.RenderPlayer",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class RenderPlayer extends RendererLivingEntity {

    @ClassInstance
    public static Class<?> RenderPlayerClass;
    @WrapMethod(mcpName = "getMainModel",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "getMainModel",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method getMainModel;
    @WrapMethod(mcpName = "doRender",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V")
    @WrapMethod(mcpName = "doRender",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V")
    public static Method doRender;

    public RenderPlayer(Object obj) {
        super(obj);
    }
    public ModelPlayer getMainModel() {
        return new ModelPlayer(invoke(getMainModel));
    }


}
