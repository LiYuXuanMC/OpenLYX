package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventAttack;
import al.nya.reflect.events.events.EventPacket;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.network.C02.C02PacketUseEntity;
import al.nya.reflect.wrapper.wraps.wrapper.network.C0B.C0BAction;
import al.nya.reflect.wrapper.wraps.wrapper.network.C0B.C0BPacketEntityAction;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetHandlerPlayClient;

public class SuperKnockBack extends Module {
    private final ModeValue mode = new ModeValue("Mode", Mode.Normal, Mode.values());
    public SuperKnockBack() {
        super("SuperKnockBack", "超级击退", ModuleType.Combat);
        addValue(mode);
    }
    @EventTarget
    public void attack(EventAttack e){
        if (mode.getValue() == Mode.Vapu) return;
        if (EntityLivingBase.isEntityLivingBase(e.getTargetEntity())) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP entityPlayerSP = mc.getThePlayer();
            NetHandlerPlayClient netHandler = mc.getNetHandler();
            if (entityPlayerSP.isSprinting())
                netHandler.addToSendQueue(new C0BPacketEntityAction(entityPlayerSP, C0BAction.STOP_SPRINTING));

            netHandler.addToSendQueue(new C0BPacketEntityAction(entityPlayerSP, C0BAction.START_SPRINTING));
            netHandler.addToSendQueue(new C0BPacketEntityAction(entityPlayerSP, C0BAction.STOP_SPRINTING));
            netHandler.addToSendQueue(new C0BPacketEntityAction(entityPlayerSP, C0BAction.START_SPRINTING));
            entityPlayerSP.setSprinting(true);
            entityPlayerSP.setServerSprintState(true);

        }
    }

    @EventTarget
    public void onPacket(EventPacket e) {
        if (mode.getValue() == Mode.Normal) return;
        if (C02PacketUseEntity.isC02PacketUseEntity(e.getPacket())) {
            EntityPlayerSP thePlayer = mc.getThePlayer();
            if (thePlayer.isNull()) return;
            boolean serverSprintState = thePlayer.getServerSprintState();
            thePlayer.setServerSprintState(!serverSprintState);
        }
        if (C0BPacketEntityAction.isC0BPacketEntityActionClass(e.getPacket()) && new C0BPacketEntityAction(e.getPacket().getWrapObject()).getAction() == C0BAction.STOP_SPRINTING)
            mc.getThePlayer().getSendQueue().addToSendQueue(new C0BPacketEntityAction(mc.getThePlayer(), C0BAction.START_SPRINTING));
    }

    enum Mode {
        Normal,
        Vapu
    }
}
