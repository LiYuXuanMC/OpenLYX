package al.logger.client.wrapper.LoggerMC.render;

import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.ReflectUtil;
import al.logger.client.wrapper.annotations.ClassInstance;
import al.logger.client.wrapper.annotations.WrapMethod;
import al.logger.client.wrapper.annotations.WrapperClass;
import al.logger.client.wrapper.IWrapper;

import java.lang.reflect.Method;

@WrapperClass(mcpName = "net.minecraft.client.renderer.WorldRenderer",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
@WrapperClass(mcpName = "net.minecraft.client.renderer.BufferBuilder",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
//ToDo: Use MethodHandle
public class WorldRenderer extends IWrapper {
    @ClassInstance
    public static Class WorldRendererClass;
    @WrapMethod(mcpName = "begin",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "begin",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method begin;
    @WrapMethod(mcpName = "pos",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "pos",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method pos;
    @WrapMethod(mcpName = "endVertex",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "endVertex",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method endVertex;
    @WrapMethod(mcpName = "color",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(FFFF)Lnet/minecraft/client/renderer/WorldRenderer;")
    @WrapMethod(mcpName = "color",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(FFFF)Lnet/minecraft/client/renderer/BufferBuilder;")
    public static Method color_4f;
    @WrapMethod(mcpName = "color",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla},signature = "(IIII)Lnet/minecraft/client/renderer/WorldRenderer;")
    @WrapMethod(mcpName = "color",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla},signature = "(IIII)Lnet/minecraft/client/renderer/BufferBuilder;")
    public static Method color_4I;
    @WrapMethod(mcpName = "putColorMultiplier",targetMap = {Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    @WrapMethod(mcpName = "putColorMultiplier",targetMap = {Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    public static Method putColorMultiplier;
    @WrapMethod(mcpName = "tex",targetMap={Environment.MINECRAFT_VERSION_1_12_2_Forge,Environment.MINECRAFT_VERSION_1_12_2_Vanilla})
    @WrapMethod(mcpName = "tex",targetMap={Environment.MINECRAFT_VERSION_1_8_9_Forge,Environment.MINECRAFT_VERSION_1_8_9_Vanilla})
    public static Method tex;

    public WorldRenderer(Object obj) {
        super(obj);
    }
    public void begin(int glMode, VertexFormat format)
    {
        invoke(begin,glMode,format.getWrappedObject());
    }
    public WorldRenderer pos(double x, double y, double z)
    {
        invoke(pos,x,y,z);
        return this;
    }
    public void endVertex()
    {
        invoke(endVertex);
    }
    public WorldRenderer color(float red, float green, float blue, float alpha)
    {
        invoke(color_4f,red,green,blue,alpha);
        return this;
    }
    public WorldRenderer color(int i1,int i2,int i3,int i4) {
        invoke(color_4I,i1,i2,i3,i4);
        return this;
    }

    public WorldRenderer tex(double u2, double v1) {
        invoke(tex,u2,v1);
        return this;
    }
}
