package cc.systemv.rave.feature.component;

import cc.systemv.rave.event.Priority;
import cc.systemv.rave.event.annotation.Listener;
import cc.systemv.rave.event.events.EventPreMotion;
import cc.systemv.rave.event.events.EventPreUpdate;
import cc.systemv.rave.utils.InstanceAccess;
import cc.systemv.rave.utils.player.MovementFix;
import cc.systemv.rave.utils.player.MovementUtil;
import cc.systemv.rave.utils.player.RotationUtil;
import cc.systemv.rave.utils.vector.Vector2f;

public class RotationComponent extends InstanceAccess {
    private Vector2f rotations, lastRotations, lastServerRotations, targetRotations;
    private boolean active, smoothed;
    private double rotationSpeed;
    private MovementFix correctMovement;

    public RotationComponent(){

    }

    public void setRotations(final Vector2f rotations, final double rotationSpeed, final MovementFix correctMovement) {
        this.targetRotations = rotations;
        this.rotationSpeed = rotationSpeed * 18;
        this.correctMovement = correctMovement;
        active = true;

        smooth();
    }
    @Listener(priority = Priority.Lowest)
    public void onPreUpdate(EventPreUpdate preUpdate){

        if (!active || rotations == null || lastRotations == null || targetRotations == null || lastServerRotations == null) {
            rotations = lastRotations = targetRotations = lastServerRotations = new Vector2f(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        }

        if (active) {
            smooth();
        }

//        mc.thePlayer.rotationYaw = rotations.x;
//        mc.thePlayer.rotationPitch = rotations.y;

        if (correctMovement == MovementFix.BACKWARDS_SPRINT && active) {
            if (Math.abs(rotations.x - Math.toDegrees(MovementUtil.direction())) > 45) {
                mc.gameSettings.keyBindSprint.setPressed(false);
                mc.thePlayer.setSprinting(false);
            }
        }
    }
    @Listener(priority = Priority.Lowest)
    public void onPreMotion(EventPreMotion event){
        if (active && rotations != null) {
            final float yaw = rotations.x;
            final float pitch = rotations.y;

            event.setYaw(yaw);
            event.setPitch(pitch);

//            mc.thePlayer.rotationYaw = yaw;
//            mc.thePlayer.rotationPitch = pitch;

            mc.thePlayer.renderYawOffset = yaw;
            mc.thePlayer.rotationYawHead = yaw;
            //mc.thePlayer.renderPitchHead = pitch;

            lastServerRotations = new Vector2f(yaw, pitch);

            if (Math.abs((rotations.x - mc.thePlayer.rotationYaw) % 360) < 1 && Math.abs((rotations.y - mc.thePlayer.rotationPitch)) < 1) {
                active = false;

                this.correctDisabledRotations();
            }

            lastRotations = rotations;
        } else {
            lastRotations = new Vector2f(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        }

        targetRotations = new Vector2f(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        smoothed = false;
    }
    private void correctDisabledRotations() {
        final Vector2f rotations = new Vector2f(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        final Vector2f fixedRotations = RotationUtil.resetRotation(RotationUtil.applySensitivityPatch(rotations, lastRotations));

        mc.thePlayer.rotationYaw = fixedRotations.x;
        mc.thePlayer.rotationPitch = fixedRotations.y;
    }
    public void smooth() {
        if (!smoothed) {
            final float lastYaw = lastRotations.x;
            final float lastPitch = lastRotations.y;
            final float targetYaw = targetRotations.x;
            final float targetPitch = targetRotations.y;

            rotations = RotationUtil.smooth(new Vector2f(lastYaw, lastPitch), new Vector2f(targetYaw, targetPitch),
                    rotationSpeed + Math.random());

            /*if (correctMovement == MovementFix.NORMAL || correctMovement == MovementFix.TRADITIONAL) {
                mc.thePlayer.movementYaw = rotations.x;
            }

            mc.thePlayer.velocityYaw = rotations.x;

             */
        }

        smoothed = true;

        /*
         * Updating MouseOver
         */
        mc.entityRenderer.getMouseOver(1);
    }

}
