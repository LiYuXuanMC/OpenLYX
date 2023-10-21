package al.nya.reflect.features.modules.World;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.*;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.disablers.*;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;

import java.util.ArrayList;
import java.util.List;

public class Disabler extends Module {
    public static Disabler INSTANCE;
    public final ModeValue mode = new ModeValue("Mode", DisablerMode.Hypixel, DisablerMode.values()) {
        @Override
        public void onValueChange() {
            if (isEnable()) {
                onDisable();
                for (DisableSubModule subModule : subModules) {
                    if (subModule.getMode() == getValue()) {
                        using = subModule;
                    }
                }
                onEnable();
            }else {
                for (DisableSubModule subModule : subModules) {
                    if (subModule.getMode() == getValue()) {
                        using = subModule;
                    }
                }
            }
        }
    };
    private final List<DisableSubModule> subModules = new ArrayList<DisableSubModule>();
    public DisableSubModule using = null;
    public Disabler() {
        super("Disabler",ModuleType.World);
        INSTANCE = this;
        subModules.add(new HypixelDisabler());
        subModules.add(new VulcanDisabler());
        if (MargeleAntiCheatDetector.isMAC() && Reflect.USER.isBeta())
        subModules.add(new MargelesAntiCheatDisabler());
        for (DisableSubModule subModule : subModules) {
            if (subModule.getMode() == mode.getValue()){
                using = subModule;
            }
        }
        addValue(mode);
    }

    @EventTarget
    public void onPre(EventPreUpdate pre) {
        using.preUpdate(pre);
    }

    @EventTarget
    public void onPacket(EventPacket packet) {
        using.packet(packet);
    }

    @EventTarget
    public void onUpdate(EventUpdate update){
        using.update(update);
    }

    @EventTarget
    public void onWorld(EventWorldLoad worldLoad) {
        using.onWorld(worldLoad);
    }

    @EventTarget
    public void onMove(EventMove eventMove) {
        using.onMove(eventMove);
    }

    @EventTarget
    public void onLoop(EventLoop loop) {
        using.loop(loop);
    }

    @Override
    public void onDisable() {
        using.disable();
        super.onDisable();
    }

    @Override
    public void onEnable() {
        using.enable();
        super.onEnable();
    }

    @EventTarget
    public void onRender2D(EventRender2D eventRender2D) {
        using.render2D(eventRender2D);
    }

    @Override
    public String getSuffix() {
        if (using != null){
            if (using.getSuffix() != null){
                return mode.getValue().name() + " " + using.getSuffix();
            }
        }
        return mode.getValue().name();
    }
}
