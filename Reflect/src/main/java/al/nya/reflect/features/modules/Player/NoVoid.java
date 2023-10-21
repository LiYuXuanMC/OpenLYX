package al.nya.reflect.features.modules.Player;

import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.Scaffold;
import al.nya.reflect.utils.FallingPlayer;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;
import al.nya.reflect.wrapper.wraps.wrapper.network.S08.S08PacketPlayerPosLook;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;

import java.util.ArrayList;

public class NoVoid extends Module {

    /* BLINK ANTIVOID */
    private boolean blink = false;
    private boolean canBlink = false;
    private double posX = 0.0;
    private double posY = 0.0;
    private double posZ = 0.0;
    private double motionX = 0.0;
    private double motionY = 0.0;
    private double motionZ = 0.0;
    ArrayList<Packet> packetCache = new ArrayList<>();
    /* BLINK ANTIVOID */
    public static boolean isPullingBack = false;
    public static ModeValue mode = new ModeValue("PullBackMode",PullBackMode.Movement,PullBackMode.values());
    public static DoubleValue fallDist = new DoubleValue("FallDist",10,1,5,"0.0") {
        @Override
        public boolean show() {
            return mode.getValue() == PullBackMode.Movement;
        }
    };
    public static DoubleValue startFallDistValue = new DoubleValue("StartFallDistance",5,0,2,"0.0") {
        @Override
        public boolean show() {
            return mode.getValue() == PullBackMode.Blink;
        }
    };
    public static DoubleValue maxFallDistValue = new DoubleValue("MaxFallDistance",20,5,10,"0.0") {
        @Override
        public boolean show() {
            return mode.getValue() == PullBackMode.Blink;
        }
    };
    public static OptionValue resetMotionValue = new OptionValue("ResetMotion",false) {
        @Override
        public boolean show() {
            return mode.getValue() == PullBackMode.Blink;
        }
    };
    public static OptionValue autoScaffoldValue = new OptionValue("AutoScaffold",false) {
        @Override
        public boolean show() {
            return mode.getValue() == PullBackMode.Blink;
        }
    };

    @Override
    public void onEnable() {
        blink = false;
        canBlink = false;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public NoVoid() {
        super("NoVoid", ModuleType.Player);
        addValues(mode,fallDist,startFallDistValue,maxFallDistValue,resetMotionValue,autoScaffoldValue);
    }
    public static boolean isInVoid() {
        for (int i = 0; i <= 128; i++) {
            if (MovementUtils.isOnGround(i)) {
                return false;
            }
        }
        return true;
    }

    @EventTarget
    public void onUpdate(EventUpdate e) {
        if (mode.getValue() == PullBackMode.Blink) {
            EntityPlayerSP thePlayer = mc.getThePlayer();
            if (!blink) {
                BlockPos collide = new FallingPlayer(thePlayer.getPosX(), thePlayer.getPosY(), thePlayer.getPosZ(), 0.0, 0.0, 0.0, 0F, 0F, 0F, 0F).findCollision(50);
                if (canBlink && ((collide == null || collide.isNull()) || (thePlayer.getPosY() - collide.getY()) > startFallDistValue.getValue())) {
                    posX = thePlayer.getPosX();
                    posY = thePlayer.getPosY();
                    posZ = thePlayer.getPosZ();
                    motionX = thePlayer.getMotionX();
                    motionY = thePlayer.getMotionY();
                    motionZ = thePlayer.getMotionZ();

                    packetCache.clear();
                    blink = true;
                }
                if (thePlayer.isOnGround()) {
                    canBlink = true;
                }
            } else {
                if (thePlayer.getFallDistance()> maxFallDistValue.getValue()) {
                    thePlayer.setPositionAndUpdate(posX, posY, posZ);
                    if (resetMotionValue.getValue()) {
                        thePlayer.setMotionX(0.0);
                        thePlayer.setMotionY(0.0);
                        thePlayer.setMotionZ(0.0);
                        thePlayer.setJumpMovementFactor(0.00f);
                    } else {
                        thePlayer.setMotionX(motionX);
                        thePlayer.setMotionY(motionY);
                        thePlayer.setMotionZ(motionZ);
                        thePlayer.setJumpMovementFactor(0.00f);
                    }
                    if (autoScaffoldValue.getValue()) {
                        ModuleManager.getModule(Scaffold.class).setEnable(true);
                    }
                    packetCache.clear();
                    blink = false;
                    canBlink = false;
                } else if (thePlayer.isOnGround()) {
                    blink = false;
                    for (Packet packet : packetCache) {
                        mc.getNetHandler().getNetworkManager().sendPacketNoEvent(packet);
                    }
                }
            }
        }
    }

    @EventTarget
    public void onPacket(EventPacket evt){
        if (mode.getValue() == PullBackMode.Movement){
            if (evt.getEventType() == EventType.SendPacket){
                if (C03PacketPlayer.isPacketPlayer(evt.getPacket())){
                    if (isInVoid() && mc.getThePlayer().getFallDistance() > fallDist.getValue().floatValue()){
                        if (!isPullingBack){
                            EntityPlayerSP thePlayer = mc.getThePlayer();
                            mc.getNetHandler().getNetworkManager().sendPacketNoEvent(new C04PacketPlayerPosition(thePlayer.getPosX(), thePlayer.getPosY() + 11, thePlayer.getPosZ(), false));
                            isPullingBack = true;
                        }
                    }
                }
            }
            if (evt.getEventType() == EventType.RecievePacket){
                if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(evt.getPacket())){
                    isPullingBack = false;
                }
            }
        }
        if (mode.getValue() == PullBackMode.Blink) {
            if (blink && (C03PacketPlayer.isPacketPlayer(evt.getPacket()))) {
                packetCache.add(evt.getPacket());
                evt.setCancel(true);
            }
        }

    }
    public enum PullBackMode {
        Movement,
        Blink
    }
}
