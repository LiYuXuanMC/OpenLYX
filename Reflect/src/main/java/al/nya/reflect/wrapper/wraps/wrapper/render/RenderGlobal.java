package al.nya.reflect.wrapper.wraps.wrapper.render;

import al.nya.reflect.wrapper.Maps;
import al.nya.reflect.wrapper.wraps.annotation.WrapMethod;
import al.nya.reflect.wrapper.wraps.annotation.WrapperClass;
import al.nya.reflect.wrapper.wraps.wrapper.IWrapper;

import java.lang.reflect.Method;
@WrapperClass(mcpName = "net.minecraft.client.renderer.RenderGlobal", targetMap = Maps.Srg1_8_9)
@WrapperClass(mcpName = "net.minecraft.client.renderer.RenderGlobal", targetMap = Maps.Srg1_12_2)
public class RenderGlobal extends IWrapper {
    @WrapMethod(mcpName = "loadRenderers", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "loadRenderers", targetMap = Maps.Srg1_12_2)
    public static Method loadRenderers;
    @WrapMethod(mcpName = "markBlockRangeForRenderUpdate", targetMap = Maps.Srg1_8_9)
    @WrapMethod(mcpName = "markBlockRangeForRenderUpdate", targetMap = Maps.Srg1_12_2)
    public static Method markBlockRangeForRenderUpdate;
    public RenderGlobal(Object obj) {
        super(obj);
    }
    public void loadRenderers(){
        invoke(loadRenderers);
    }
    public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
        invoke(markBlockRangeForRenderUpdate,x1,y1,z1,x2,y2,z2);
    }
}
