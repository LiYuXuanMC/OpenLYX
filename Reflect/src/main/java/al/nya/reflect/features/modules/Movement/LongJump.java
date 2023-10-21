package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventJump;
import al.nya.reflect.events.events.EventMove;
import al.nya.reflect.events.events.EventPullback;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.entity.MovementUtils;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.utils.EnumFacing;

/**
 * Do not skid Hypixel Mode thx
 */
public class LongJump extends Module {
    public ModeValue mode = new ModeValue("Mode", Mode.AACv1, Mode.values());
    public OptionValue autoJump = new OptionValue("AutoJump",false);
    private boolean teleported = false;
    private double motionY,speed;
    private int stage;
    private boolean jump;
    public LongJump() {
        super("LongJump", ModuleType.Movement);
        addValues(mode,
                autoJump);
    }
    public void onEnable(){
        if (mode.getValue() == Mode.Hypixel){
            if (!(Reflect.USER.rank.equals("Dev"))){
                ClientUtil.printChat(ClientUtil.Level.WARNING,"Access Denied");
                setEnableNoNotification(false);
            }else {
                if (!mc.getThePlayer().isOnGround()){
                    NotificationPublisher.queue("Longjump","You must stand on a block",2500, NotificationType.ERROR);
                    setEnableNoNotification(false);
                    return;
                }
                speed = 0;
                motionY = 0;
                stage = 0;
                jump = false;
            }
        }
    }
    @EventTarget
    public void onMove(EventMove move){
        /*final double[] motY_offsets = {
                .81999998688698,
                .888,
                .878,
                .865,
                .843,
                .816,
                .79,
                .7268,
                .638,
                .445,
                -.007
        };

        final double[] speeds = {
                1.2253669972219714,
                0.4459504302767603,
                0.43929448531934745,
                0.43273788235211147,
                0.4262791386658409,
                0.4199167936812093,
                0.41364940861849553,
                0.40747556617205316,
                0.4013938701901575,
                0.39540294535897424,
                0.3895014368915771,
                0.38368801022175647,
                0.37796135070203635,
                0.3723204407431538,
                0.36676399950775584,
                0.36129037145087656,
                0.355897984551988,
                0.35058608093951643,
                0.3453535407406495,
                0.3401991040483928,
                0.3351215092756337,
                0.33011969944283465
        };
        EntityPlayerSP thePlayer = mc.getThePlayer();
        switch (++stage){
            case 1:
                thePlayer.setPosition(thePlayer.getPosX(),thePlayer.getPosY() + 0.004 * Math.random(),thePlayer.getPosZ());
                PlayerUtils.selfDamage();
                thePlayer.setPosition(thePlayer.getPosX(), MathHelper.floor_double(thePlayer.getPosY()),thePlayer.getPosZ());
                motionY = motY_offsets[0];
                jump = true;
                break;
            case 11:
                motionY = motY_offsets[10];
                break;
            default:
                if (stage < 11)
                    motionY *= motY_offsets[stage - 1];
        }

        if (stage <= 21)
            speed = MovementUtils.getBaseMoveSpeed() *  speeds[stage - 1];

        speed = Math.max(MovementUtils.getBaseMoveSpeed(),speed);
        PlayerUtil.setSpeed(move,speed);

        if (stage <= 11) {
            move.setY(motionY);
            thePlayer.setMotionY(motionY);
        }

        if (jump && thePlayer.isOnGround() && stage > 3) setEnable(false);

         */
    }
    @EventTarget
    public void onPullBack(EventPullback pullback){

    }

    @EventTarget
    public void onUpdate(EventUpdate update){
        EntityPlayerSP thePlayer = Minecraft.getMinecraft().getThePlayer();
        if (mode.getValue() == Mode.AACv1) {
            thePlayer.setMotionY(thePlayer.getMotionX() + 0.05999);
            MovementUtils.strafe(MovementUtils.getSpeed() * 1.08f);
        } else if (mode.getValue() == Mode.AACv2) {
            thePlayer.setJumpMovementFactor(0.09f);
            thePlayer.setMotionY(thePlayer.getMotionY() + 0.0132099999999999999999999999999);
            thePlayer.setJumpMovementFactor(0.08f);
            MovementUtils.strafe();
        } else if (mode.getValue() == Mode.AACv3) {
            if (thePlayer.getFallDistance() > 0.5f && !teleported) {
                double value = 3.0;
                double x = 0.0;
                double z = 0.0;
                Enum horizontalFacing = mc.getThePlayer().getHorizontalFacing();
                if (horizontalFacing == EnumFacing.NORTH) {
                    z -= value;
                } else if (horizontalFacing == EnumFacing.EAST) {
                    x += value;
                } else if (horizontalFacing == EnumFacing.SOUTH) {
                    z += value;
                } else if (horizontalFacing == EnumFacing.WEST) {
                    x -= value;
                }
                thePlayer.setPosition(thePlayer.getPosX() + x, thePlayer.getPosY(), thePlayer.getPosZ() + z);
                teleported = true;
            }
        }
    }


    @EventTarget
    public void onJump(EventJump event) {
        if (mode.getValue() == Mode.AACv3) {
            teleported = false;
        }
    }

    public enum Mode{
        AACv1,
        AACv2,
        AACv3,
        Hypixel
    }
}
