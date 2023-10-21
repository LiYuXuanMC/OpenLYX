package al.nya.reflect.utils.render;


import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;

public class RenderHelper {
    public static void disableStandardItemLighting()
    {
        GlStateManager.disableLighting();
        GlStateManager.disableLight(0);
        GlStateManager.disableLight(1);
        GlStateManager.disableColorMaterial();
    }
    public static void enableStandardItemLighting()
    {
        al.nya.reflect.wrapper.wraps.wrapper.render.RenderHelper.enableStandardItemLighting();
    }
}
