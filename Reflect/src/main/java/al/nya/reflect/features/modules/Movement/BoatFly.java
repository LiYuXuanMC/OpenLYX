package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.GameSettings;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityBoat;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovementInput;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

public class BoatFly extends Module {
    public final DoubleValue speed = new DoubleValue("Speed", 10.0D, 1.0D, 3.0D, "0.0");
    public final DoubleValue verticalSpeed = new DoubleValue("VerticalSpeed", 10.0D, 1.0D, 3.0D, "0.0");
    public final OptionValue noKick = new OptionValue("NoKick", true);
    public final OptionValue packet = new OptionValue("Packet", true);
    public final DoubleValue packets = new DoubleValue("Packets", 5.0D, 1.0D, 3.0D, "0.0");
    public final DoubleValue interact = new DoubleValue("Interact", 20.0D, 1.0D, 2.0D, "0.0");

    private EntityBoat target;
//    private int teleportID;

    public BoatFly() {
        super("BoatFly", ModuleType.Movement);
        addValues(
                speed,
                verticalSpeed,
                noKick,
                packet,
                packets,
                interact
        );
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        WorldClient world = mc.getTheWorld();
        Entity ridingEntity = thePlayer.getRidingEntity();
        if (thePlayer.isNull() || world.isNull() || ridingEntity.isNull()) {
            return;
        }
        GameSettings gameSettings = mc.getGameSettings();
        if (EntityBoat.isEntityBoat(ridingEntity)) {
            this.target = new EntityBoat(ridingEntity.getWrapObject());
        }

//        ridingEntity.setNoGravity(true);
        ridingEntity.setMotionY(0.0);
        if (gameSettings.getKeyBindJump().isKeyDown()) {
            ridingEntity.setOnGround(false);
            ridingEntity.setMotionY(this.verticalSpeed.getValue() / 10.0);
        }
        if (gameSettings.getKeyBindSprint().isKeyDown()) {
            ridingEntity.setOnGround(false);
            ridingEntity.setMotionY(-this.verticalSpeed.getValue() / 10.0);
        }

        double[] normalDir = this.directionSpeed(this.speed.getValue() / 2.0);
        MovementInput movementInput = thePlayer.getMovementInput();
        if (movementInput.getMoveStrafe() != 0.0f || movementInput.getMoveForward() != 0.0f) {
            ridingEntity.setMotionX(normalDir[0]);
            ridingEntity.setMotionZ(normalDir[1]);
        } else {
            ridingEntity.setMotionX(0.0);
            ridingEntity.setMotionZ(0.0);
        }

        if (noKick.getValue()) {
            if (gameSettings.getKeyBindJump().isKeyDown()) {
                if (thePlayer.getTicksExisted() % 8 < 2) {
                    ridingEntity.setMotionY(-0.04f);
                }
            } else if (thePlayer.getTicksExisted() % 8 < 4) {
                ridingEntity.setMotionY(-0.08f);
            }
        }

        if (this.packet.getValue()) {
            this.handlePackets(thePlayer, ridingEntity.getMotionX(), ridingEntity.getMotionY(), ridingEntity.getMotionZ());
        }
    }

    public void handlePackets(EntityPlayerSP thePlayer, double x, double y, double z) {
        Entity ridingEntity = thePlayer.getRidingEntity();
        if (ridingEntity.isNull()) {
            return;
        }
//        NetworkManager netWorkManager = thePlayer.getSendQueue().getNetworkManager();
        Vec3 vec = new Vec3(x, y, z);
        Vec3 position = thePlayer.getRidingEntity().getPositionVector().add(vec);
        thePlayer.getRidingEntity().setPosition(position.getXCoord(), position.getYCoord(), position.getZCoord());
//        netWorkManager.sendPacket(new CPacketVehicleMove(mc.player.getRidingEntity())); /* 1.8没这东西? */
//        for (int i = 0; i < this.packets.getValue(); ++i) {
//            netWorkManager.sendPacket(new CPacketConfirmTeleport(this.teleportID++));
//        }
    }

    private double[] directionSpeed(double speed) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        MovementInput movementInput = thePlayer.getMovementInput();
        float forward = movementInput.getMoveForward();
        float side = movementInput.getMoveStrafe();
        float yaw = thePlayer.getPrevRotationYaw() + (thePlayer.getRotationYaw() - thePlayer.getPrevRotationYaw()) * mc.getTimer().getRenderPartialTicks();
        if (forward != 0.0f) {
            if (side > 0.0f) {
                yaw += (float) (forward > 0.0f ? -45 : 45);
            } else if (side < 0.0f) {
                yaw += (float) (forward > 0.0f ? 45 : -45);
            }
            side = 0.0f;
            if (forward > 0.0f) {
                forward = 1.0f;
            } else if (forward < 0.0f) {
                forward = -1.0f;
            }
        }
        double sin = Math.sin(Math.toRadians(yaw + 90.0f));
        double cos = Math.cos(Math.toRadians(yaw + 90.0f));
        double posX = (double) forward * speed * cos + (double) side * speed * sin;
        double posZ = (double) forward * speed * sin - (double) side * speed * cos;
        return new double[]{posX, posZ};
    }
}
