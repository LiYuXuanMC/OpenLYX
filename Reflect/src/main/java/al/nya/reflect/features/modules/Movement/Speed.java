package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.*;
import al.nya.reflect.events.events.world.EventWorldLoad;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.Movement.speeds.SpeedModules;
import al.nya.reflect.features.modules.Movement.speeds.Speeds;
import al.nya.reflect.features.modules.Movement.speeds.aac.*;
import al.nya.reflect.features.modules.Movement.speeds.hypixel.Hypixel;
import al.nya.reflect.features.modules.Movement.speeds.hypixel.HypixelBunny;
import al.nya.reflect.features.modules.Movement.speeds.hypixel.HypixelStrafe;
import al.nya.reflect.features.modules.Movement.speeds.other.CSGO;
import al.nya.reflect.features.modules.Movement.speeds.other.Edit;
import al.nya.reflect.features.modules.Movement.speeds.other.GroundStrafeHop;
import al.nya.reflect.features.modules.Movement.speeds.hypixel.HypixelLowHop;
import al.nya.reflect.features.modules.Movement.speeds.other.Legit;
import al.nya.reflect.features.modules.Movement.speeds.vulcan.VulcanHop;
import al.nya.reflect.gui.notification.Notification;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.value.Value;

import java.util.ArrayList;
import java.util.List;

public class Speed extends Module {
    private final List<SpeedModules> speeds = new ArrayList<SpeedModules>();
    private final OptionValue autoDisable = new OptionValue("AutoDisable", true);
    public final ModeValue mode = new ModeValue("Mode", Speeds.Legit, Speeds.values()) {
        @Override
        public void onValueChange() {
            valueChanged();
        }
    };
    public static DoubleValue timerSpeed = new DoubleValue("TimerSpeed", 1.5D, 0.1D, 1.0D, "0.00");
    public SpeedModules speed = null;

    public Speed() {
        super("Speed", ModuleType.Movement);
        addValue(mode);
        addValue(timerSpeed);
        addValue(autoDisable);
        speeds.add(new Legit());
        speeds.add(new GroundStrafeHop());
        speeds.add(new HypixelLowHop());
        speeds.add(new Hypixel());
        speeds.add(new CSGO());
        speeds.add(new HypixelStrafe());
        speeds.add(new HypixelBunny());
        speeds.add(new AAC2BHop());
        speeds.add(new AAC3BHop());
        speeds.add(new AAC4BHop());
        speeds.add(new AAC5BHop());
        speeds.add(new AAC6BHop());
        speeds.add(new AAC7BHop());
        speeds.add(new AACBHop());
        speeds.add(new VulcanHop());
        speeds.add(new Edit());
        for (SpeedModules speed1 : speeds) {
            for (Value value : speed1.getValues()) {
                addValue(value);
            }
        }
        for (SpeedModules speedModules : speeds) {
            if (speedModules.getType() == mode.getValue()){
                this.speed = speedModules;
            }
        }
    }
    @EventTarget
    public void onLagBack(EventPullback pullback){
        if (autoDisable.getValue()) {
            setEnableNoNotification(false);
            NotificationPublisher.queue("Disable",this.getName()+ " Has Been Disabled(Lag back)", 2000, NotificationType.ERROR);
        }
    }
    @EventTarget
    public void onWorldLoad(EventWorldLoad worldLoad){
        if (autoDisable.getValue()) setEnable(false);
    }
    @EventTarget
    public void onUpdate(EventUpdate update){
        speed.onUpdate(update);
    }

    @EventTarget
    public void onMove(EventMove move) {
        speed.onMove(move);
    }

    @EventTarget
    public void onPre(EventPreUpdate pre) {
        speed.onPre(pre);
    }

    @EventTarget
    public void onJump(EventJump jump) {
        speed.onJump(jump);
    }

    public void valueChanged() {
        if (isEnable()) {
            speed.onDisable();
        }
        for (SpeedModules speedModules : speeds) {
            if (speedModules.getType() == mode.getValue()) {
                this.speed = speedModules;
            }
        }
        if (isEnable()) {
            speed.onEnable();
        }
    }
    @EventTarget
    public void onTick(EventTick e) {
        speed.onTick(e);
    }
    @Override
    public void onEnable(){
        speed.onEnable();
    }
    @Override
    public void onDisable(){
        speed.onDisable();
    }
    @Override
    public String getSuffix(){
        return speed.getType().name();
    }
}
