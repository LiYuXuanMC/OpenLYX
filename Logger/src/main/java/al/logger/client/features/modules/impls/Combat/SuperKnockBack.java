package al.logger.client.features.modules.impls.Combat;


import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventAttack;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BAction;
import al.logger.client.wrapper.LoggerMC.network.client.C0B.C0BPacketEntityAction;
import al.logger.client.wrapper.LoggerMC.network.NetHandlerPlayClient;

public class SuperKnockBack extends Module {
    private final ModeValue mode = new ModeValue("Mode", Mode.Normal, Mode.values());
    public SuperKnockBack() {
        super("SuperKnockBack", Category.Combat);
        addValues(mode);
    }
    @Listener
    public void attack(EventAttack e){
        if (mode.getValue() == Mode.Normal)
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


    enum Mode {
        Normal,
    }
}
