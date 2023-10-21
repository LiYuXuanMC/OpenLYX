package com.reflectmc.reflect.features.modules.ghost;

import com.reflectmc.reflect.event.EventType;
import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.game.EventPacket;
import com.reflectmc.reflect.event.events.player.EventLivingUpdate;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.DoubleValue;
import com.reflectmc.reflect.features.values.ModeValue;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.S12PacketEntityVelocity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.network.packets.S27PacketExplosion;

public class Velocity extends Module {
    public ModeValue mode = new ModeValue("Mode",Mode.Packet,Mode.values());
    public DoubleValue horizontal = new DoubleValue("Horizontal",1,0,1,"0.00"){
        @Override
        public boolean show(){
            return mode.getValue() == Mode.Packet || mode.getValue() == Mode.Motion;
        }
    };
    public DoubleValue vertical = new DoubleValue("Vertical",1,0,1,"0.00"){
        @Override
        public boolean show(){
            return mode.getValue() == Mode.Packet;
        }
    };
    public Velocity() {
        super("Velocity",Category.Ghost);
        addValues(mode,horizontal,vertical);
    }
    @EventTarget
    public void onPacket(EventPacket packet){
        if (packet.getType() == EventType.Receive && mode.getValue() == Mode.Packet){
            if (S12PacketEntityVelocity.isS12PacketEntityVelocity(packet.getPacket())){
                S12PacketEntityVelocity s12PacketEntityVelocity = new S12PacketEntityVelocity(packet.getPacket().getWrappedObject());
                if (horizontal.getValue() == 0D && vertical.getValue() == 0D){
                    packet.cancel();
                }else {
                    s12PacketEntityVelocity.setMotionX((int) (s12PacketEntityVelocity.getMotionX()* horizontal.getValue()));
                    s12PacketEntityVelocity.setMotionY((int) (s12PacketEntityVelocity.getMotionY()* vertical.getValue()));
                    s12PacketEntityVelocity.setMotionZ((int) (s12PacketEntityVelocity.getMotionZ()* horizontal.getValue()));
                }
            }else if (S27PacketExplosion.isS27PacketExplosion(packet.getPacket())){
                packet.cancel();
            }
        }
    }
    @EventTarget
    public void onUpdate(EventLivingUpdate update){
        if (mode.getValue() == Mode.Motion){
            EntityPlayerSP thePlayer = mc.getThePlayer();
            if (thePlayer.getHurtTime()>0 && !thePlayer.isOnGround()){
                double reduce = horizontal.getValue();
                thePlayer.setMotionX(thePlayer.getMotionX() * reduce);
                thePlayer.setMotionZ(thePlayer.getMotionZ() * reduce);
            }
        }
    }
    public enum Mode{
        Packet,
        Motion
    }
}
