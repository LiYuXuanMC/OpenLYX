package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPostRenderLiving;
import al.nya.reflect.events.events.EventPreRenderLiving;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.EntitySelect;
import al.nya.reflect.wrapper.wraps.wrapper.render.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Chams extends Module {
    private final EntitySelect select = new EntitySelect(true, false, false, true);
    public Chams() {
        super("Chams", ModuleType.Visual);
        addValues(select.getValues());
    }
    @EventTarget
    public void onRenderPlayerPre(EventPreRenderLiving e) {
        if (!select.check(e.getEntity())) return;
        mc.getEntityRenderer().disableLightmap();
        GlStateManager.disableLighting();
        GL11.glEnable(32823);
        GL11.glPolygonOffset(1.0f, -1100000.0f);
    }
    @EventTarget
    public void onRenderPlayerPost(EventPostRenderLiving e) {
        if (!select.check(e.getEntity())) return;
        GL11.glDisable(32823);
        GL11.glPolygonOffset(1.0f, 1100000.0f);
        mc.getEntityRenderer().disableLightmap();
        GlStateManager.disableLighting();
    }
}
