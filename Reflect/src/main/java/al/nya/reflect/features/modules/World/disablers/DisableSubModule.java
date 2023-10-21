package al.nya.reflect.features.modules.World.disablers;

import al.nya.reflect.events.events.*;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import lombok.Getter;

public class DisableSubModule {
    @Getter
    private final DisablerMode mode;
    public Minecraft mc = Minecraft.getMinecraft();

    public DisableSubModule(DisablerMode mode) {
        this.mode = mode;
    }

    public void preUpdate(EventPreUpdate preUpdate) {
    }

    public void packet(EventPacket packet) {
    }

    public void render2D(EventRender2D eventRender2D) {
    }

    public void disable() {
    }

    public void enable() {
    }

    public void onWorld(EventWorldLoad worldLoad) {

    }

    public void onMove(EventMove update) {

    }

    public void update(EventUpdate update) {

    }

    public void loop(EventLoop loop) {

    }

    public String getSuffix() {
        return null;
    }
}
