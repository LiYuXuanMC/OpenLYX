package al.nya.reflect.events.events;

import al.nya.reflect.events.annotation.Realized;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.Visual.Blur;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.gui.GuiIngame;

//GuiIngame.renderGameOverlay
@Realized
public class EventRender2D extends EventRender {
    private GuiIngame guiIngame;
    private final float partialTicks;
    public EventRender2D(){
        partialTicks = Minecraft.getMinecraft().getTimer().getRenderPartialTicks();
        ModuleManager.getModule(Blur.class).blurScreen();
    }

    public GuiIngame getGuiIngame() {
        return guiIngame;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}
