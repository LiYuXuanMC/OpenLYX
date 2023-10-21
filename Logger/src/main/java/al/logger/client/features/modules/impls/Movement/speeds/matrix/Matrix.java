package al.logger.client.features.modules.impls.Movement.speeds.matrix;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventJump;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.misc.MathHelper;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.utils.player.PlayerUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import by.radioegor146.nativeobfuscator.Native;
import est.builder.annotations.Clear;


public class Matrix extends Module {

    public static ModeValue mode = new ModeValue("Mode" ,Mode.None,Mode.values());

    OptionValue hurt = new OptionValue("On Damage" , false);
    public DoubleValue hurttime = new DoubleValue("HurtTime" , 5D, 0D, 0d, 1.0);
    boolean vel;
    public Matrix(){
        super("Matrix", "", Category.Movement);
        addValues(mode,hurttime,hurt);
        hurt.addCallBack(() -> mode.getValue() == Mode.SemiStrafe);
        hurttime.addCallBack(() -> mode.getValue() == Mode.HurtTime);
        hurttime.addCallBack(() -> hurt.getValue());
        //velocity.addCallBack(() -> mode.getValue() == StrafeMode.Test);
    }


    @Listener
    public void onTick(EventTick e){
        if (PlayerUtils.isInLiquid()) {
            return;
        }
        if (MovementUtils.isMoving()) {
            if (mc.getThePlayer().isOnGround()) {
                MovementUtils.jump();
            }else {
                if (mode.getValue() == Mode.HurtTime){
                    if (mc.getThePlayer().getHurtTime() > hurttime.getValue()){
                        MovementUtils.strafe();
                    }
                }

                if (mode.getValue() == Mode.SemiStrafe){
                    if (hurt.getValue()){
                        if (mc.getThePlayer().getHurtTime() > hurttime.getValue()){
                            MovementUtils.strafe();
                        }else {
                            MovementUtils.strafe(0.85f);
                        }
                    }else {
                        MovementUtils.strafe(0.85f);
                    }

                }

            }

            if (mode.getValue() == Mode.GroundStrafe) {
                float speed = 1.48f;
                speed = mc.getThePlayer().getMoveStrafing() != 0.0f || mc.getThePlayer().getMoveForward() < 0.0f ? (speed -= 0.2f) : (float)((double)speed - MathHelper.getIncremental(0.00411, 0.0465123));
                if (mc.getThePlayer().isOnGround()) {
                    PlayerUtils.setSpeed(PlayerUtils.getBaseMoveSpeed() * (double)speed);
                }
            }



        } else {
            mc.getThePlayer().setMotionZ(0.0);
            mc.getThePlayer().setMotionX(0.0);
        }
    }
    @Override
    public void onPacket(EventPacket e) {

        super.onPacket(e);
    }
    @Override
    public void onJump(EventJump e) {
        if (MovementUtils.isMoving()){
            mc.getThePlayer().setMotionX(mc.getThePlayer().getMotionX() * 1.0012);
            mc.getThePlayer().setMotionZ(mc.getThePlayer().getMotionZ() * 1.0012);
        }

        super.onJump(e);
    }
    public enum Mode{
        None,
        GroundStrafe,
        HurtTime,
        SemiStrafe
    }
}
