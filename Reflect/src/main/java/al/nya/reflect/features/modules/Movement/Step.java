package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventStep;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Combat.KillAura;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.math.TimerUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Timer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;

import java.util.Arrays;
import java.util.List;

public class Step extends Module {
    //@OptionInfo(description = "Step mode selector.", name = "Mode", priority = 0)
    public ModeValue mode = new ModeValue("Mode",Mode.NCP,Mode.values());
    //@OptionInfo(description = "Timer.", name = "Timer", priority = 1)
    public OptionValue time = new OptionValue("Timer",false);
    //@OptionInfo(description = "Height.", name = "Height", priority = 2)
    public DoubleValue height = new DoubleValue("Height",2D,1D,1D,"0.0");
    //@OptionInfo(description = "Delay.", name = "Delay", priority = 3)
    public DoubleValue delay = new DoubleValue("Delay",1000D,0D,0D,"00");
    TimerUtil timer =  new TimerUtil();
    boolean resetTimer;
    public Step() {
        super("Step",ModuleType.Movement);
        addValues(mode,time,height,delay);
    }
    @Override
    public void onEnable() {
        this.resetTimer = false;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Timer timer = mc.getTimer();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (!thePlayer.isNull()) {
            thePlayer.setStepHeight(0.5F);
        }
        ClientUtil.resetTimer();
        super.onDisable();
    }

    @EventTarget
    public void onUpdate(EventUpdate update) {
        Timer timer = mc.getTimer();
        if (timer.getTimerSpeed() < 1.0f && this.mc.getThePlayer().isOnGround()) {
            ClientUtil.resetTimer();
        }
    }
    @EventTarget
    public void onStep(EventStep event) {
        TargetStrafe ts = ModuleManager.getModule(TargetStrafe.class);
        if (ts.isEnable() && ts.canStrafe(KillAura.curTarget)) {
            return;
        }
        Timer timer = mc.getTimer();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (this.resetTimer) {
            boolean resetTimer;
            resetTimer = !this.resetTimer;
            this.resetTimer = resetTimer;
            ClientUtil.resetTimer();
        }
        if (event.getEventType() == EventType.Pre) {
            if (!thePlayer.isOnGround() || !this.timer.hasPassed(this.delay.getValue().longValue())) {
                event.setHeight(0.5f);
                return;
            }
            event.setHeight(this.height.getValue().floatValue());
        }
        else if (this.mode.getValue() == Mode.NCP && event.getHeight() > 0.5) {
            final double n = thePlayer.getEntityBoundingBox().getMinY() - thePlayer.getPosY();
            if (n >= 0.625) {
                if (this.time.getValue()) {
                    final float n2 = 0.6f;
                    float n3;
                    if (n >= 1.0) {
                        n3 = Math.abs(1.0f - (float)n) * 0.33f;
                    }
                    else {
                        n3 = 0.0f;
                    }
                    timer.setTimerSpeed(n2 - n3);
                    if (timer.getTimerSpeed() <= 0.05f) {
                        timer.setTimerSpeed(0.05F);
                    }
                }
                this.resetTimer = true;
                this.ncpStep(n);
                this.timer.reset();
            }
        }
    }

    public void ncpStep(final double n) {
        final List<Double> list = Arrays.asList(0.42, 0.333, 0.248, 0.083, -0.078);
        EntityPlayerSP thePlayer = mc.getThePlayer();
        NetHandlerPlayClient sendQueue = thePlayer.getSendQueue();
        final double posX = thePlayer.getPosX();
        final double posZ = thePlayer.getPosZ();
        double posY = thePlayer.getPosY();
        if (n < 1.1) {
            double n2 = 0.42;
            double n3 = 0.75;
            if (n != 1.0) {
                n2 *= n;
                n3 *= n;
                if (n2 > 0.425) {
                    n2 = 0.425;
                }
                if (n3 > 0.78) {
                    n3 = 0.78;
                }
                if (n3 < 0.49) {
                    n3 = 0.49;
                }
            }
            if (n2 == 0.42) {
                n2 = 0.41999998688698;
            }
            sendQueue.addToSendQueue(new C04PacketPlayerPosition(posX, posY + n2, posZ, false));
            if (posY + n3 < posY + n) {
                sendQueue.addToSendQueue(new C04PacketPlayerPosition(posX, posY + n3, posZ, false));
            }
            return;
        }
        if (n < 1.6) {
            int i = 0;
            while (i < list.size()) {
                posY += list.get(i);
                sendQueue.addToSendQueue(new C04PacketPlayerPosition(posX, posY, posZ, false));
                ++i;
            }
        }
        else if (n < 2.1) {
            final double[] array = { 0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869 };
            final int length = array.length;
            int j = 0;
            while (j < length) {
                sendQueue.addToSendQueue(new C04PacketPlayerPosition(posX, posY + array[j], posZ, false));
                ++j;
            }
        }
        else {
            final double[] array2 = { 0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907 };
            final int length2 = array2.length;
            int k = 0;
            while (k < length2) {
                sendQueue.addToSendQueue(new C04PacketPlayerPosition(posX, posY + array2[k], posZ, false));
                ++k;
            }
        }
    }
    public enum Mode{
        Vanilla,
        NCP
    }
}
