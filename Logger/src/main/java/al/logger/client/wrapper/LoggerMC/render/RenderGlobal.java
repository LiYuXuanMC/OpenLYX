package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Method;
@WrapperClass(mcpName = "net.minecraft.client.renderer.RenderGlobal", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.RenderGlobal", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
public class RenderGlobal extends IWrapper {
    @WrapMethod(mcpName = "loadRenderers", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "loadRenderers", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method loadRenderers;
    @WrapMethod(mcpName = "markBlockRangeForRenderUpdate", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "markBlockRangeForRenderUpdate", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method markBlockRangeForRenderUpdate;
    @WrapMethod(mcpName = "hasCloudFog", targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "hasCloudFog", targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method hasCloudFog;
    public RenderGlobal(Object obj) {
        super(obj);
    }
    public void loadRenderers(){
        invoke(loadRenderers);
    }
    public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
        invoke(markBlockRangeForRenderUpdate,x1,y1,z1,x2,y2,z2);
    }
    public boolean hasCloudFog(double p_hasCloudFog_1_, double p_hasCloudFog_3_, double p_hasCloudFog_5_, float p_hasCloudFog_7_) {
        return (boolean) invoke(hasCloudFog,p_hasCloudFog_1_,p_hasCloudFog_3_,p_hasCloudFog_5_,p_hasCloudFog_7_);
    }
}
