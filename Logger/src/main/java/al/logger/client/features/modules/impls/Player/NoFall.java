package al.logger.client.features.modules.impls.Player;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.network.PacketUtil;
import al.logger.client.utils.player.PlayerUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.wrapper.LoggerMC.item.Items;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C03PacketPlayer;
import al.logger.client.wrapper.LoggerMC.utils.BlockPos;
import al.logger.client.wrapper.LoggerMC.utils.EnumFacing;
import al.logger.client.wrapper.LoggerMC.utils.MovingObjectPosition.MovingObjectType;


public class NoFall extends Module {
    private boolean holding;
    private static ModeValue mode = new ModeValue("Mode", NoFallMode.Packet, NoFallMode.values());

    public static DoubleValue distance = new DoubleValue("Distance", 10, 1, 3, 0.1);
    public NoFall() {
        super("NoFall", "No Fall Damage", Category.Player);
        addValues(mode,distance);
    }

    @Listener
    public void onPreUpdate(EventPreUpdate update){
        if (mode.getValue() == NoFallMode.Vanilla) {
            update.setOnGround(true);
        }
    }

    @Listener
    public void onTick(EventTick e){
        if(mc.getTheWorld().isNull()){
            return;
        }
        if (mode.getValue() == NoFallMode.Packet){
            if (mc.getThePlayer().getFallDistance() > distance.getValue()){
                PacketUtil.sendPacket(new C03PacketPlayer(true));
            }
        }

/*
        if (mode.getValue() == NoFallMode.MLG){
            if (mc.getThePlayer() == null || mc.getTheWorld() == null){
                return;
            }
            if (mc.getThePlayer().getFallDistance() > 4.0f && this.getSlotWaterBucket() != -1 && this.isMLGNeeded()) {
                mc.getThePlayer().setRotationPitch(90);
                this.swapToWaterBucket(this.getSlotWaterBucket());
            }
            if (mc.getThePlayer().getFallDistance() > 4.0f && this.isMLGNeeded() && mc.getObjectMouseOver().getTypeOfHit() == MovingObjectType.BLOCK) {
                BlockPos pos = new BlockPos(mc.getThePlayer().getPosX(), mc.getThePlayer().getPosY() - getDistanceToFall() - 1.0, mc.getThePlayer().getPosZ());
                this.placeWater(pos, EnumFacing.UP);
                if (mc.getThePlayer().getHeldItem().getItem() == Items.bucket) {
                    Thread thr = new Thread(() -> {
                        try {
                            Thread.sleep(50);
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                        ReflectionUtil.rightClickMouse();
                    });
                    thr.start();
                }
                mc.getThePlayer().setFallDistance( 0.0f);
            }
        }

 */
    }

    public enum NoFallMode {
        Packet,
        MLG,
        Vanilla
    }

}
