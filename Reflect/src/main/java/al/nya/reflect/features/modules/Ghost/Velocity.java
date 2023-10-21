package al.nya.reflect.features.modules.Ghost;

import al.nya.reflect.events.EventType;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C03PacketPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C04PacketPlayerPosition;
import al.nya.reflect.wrapper.wraps.wrapper.network.C03.C05PacketPlayerLook;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetworkManager;
import al.nya.reflect.wrapper.wraps.wrapper.network.Packet;
import al.nya.reflect.wrapper.wraps.wrapper.network.S12PacketEntityVelocity;
import al.nya.reflect.wrapper.wraps.wrapper.network.S27PacketExplosion;

import java.util.ArrayList;
import java.util.List;

public class Velocity extends Module {
    public ModeValue mode = new ModeValue("Mode",Mode.Packet,Mode.values());
    public DoubleValue horizontal = new DoubleValue("Horizontal",1,0,1,"0.00"){
        @Override
        public boolean show(){
            return mode.getValue() != Mode.Simple;
        }
    };
    public DoubleValue vertical = new DoubleValue("Vertical",1,0,1,"0.00"){
        @Override
        public boolean show(){
            return mode.getValue() == Mode.Packet;
        }
    };
    public Velocity() {
        super("Velocity",ModuleType.Ghost);
        addValue(mode);
        addValue(horizontal);
        addValue(vertical);
    }
    @EventTarget
    public void onPacket(EventPacket evt){
        if (evt.getEventType() == EventType.RecievePacket && mode.getValue() == Mode.Packet){
            if (S12PacketEntityVelocity.isS12PacketEntityVelocity(evt.getPacket())){
                S12PacketEntityVelocity s12PacketEntityVelocity = new S12PacketEntityVelocity(evt.getPacket().getWrapObject());
                if (horizontal.getValue() == 0D && vertical.getValue() == 0D){
                    evt.setCancel(true);
                }else {
                    s12PacketEntityVelocity.setMotionX((int) (s12PacketEntityVelocity.getMotionX()* horizontal.getValue()));
                    s12PacketEntityVelocity.setMotionY((int) (s12PacketEntityVelocity.getMotionY()* vertical.getValue()));
                    s12PacketEntityVelocity.setMotionZ((int) (s12PacketEntityVelocity.getMotionZ()* horizontal.getValue()));
                }
            }else if (S27PacketExplosion.isS27PacketExplosion(evt.getPacket())){
                evt.setCancel(true);
            }
        }
        if (evt.getEventType() == EventType.RecievePacket && mode.getValue() == Mode.Simple){
            if (S12PacketEntityVelocity.isS12PacketEntityVelocity(evt.getPacket())) {
                S12PacketEntityVelocity s12PacketEntityVelocity = new S12PacketEntityVelocity(evt.getPacket().getWrapObject());
                if (mc.getThePlayer().getHurtTime() >= 0){
                    if (s12PacketEntityVelocity.getEntityID() == mc.getThePlayer().getEntityId()){
                        evt.setCancel(true);
                    }
                }
            }
        }
    }
    @EventTarget
    public void onUpdate(EventUpdate update){
        if (mode.getValue() == Mode.Motion){
            EntityPlayerSP thePlayer = mc.getThePlayer();
            if (thePlayer.getHurtTime()>0 && !thePlayer.isOnGround()){
                double reduce = horizontal.getValue();
                thePlayer.setMotionX(thePlayer.getMotionX() * reduce);
                thePlayer.setMotionZ(thePlayer.getMotionZ() * reduce);
            }
        }
    }
    @Override
    public String getSuffix(){
        return mode.getValue().name();
    }
    public enum Mode{
        Packet,
        Motion,
        Simple
    }
}
