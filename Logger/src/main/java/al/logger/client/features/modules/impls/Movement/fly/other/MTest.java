package al.logger.client.features.modules.impls.Movement.fly.other;

import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.player.EventUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.wrapper.LoggerMC.network.client.C03.C04PacketPlayerPosition;
import al.logger.client.wrapper.LoggerMC.network.server.S08.S08PacketPlayerPosLook;

public class MTest extends Module {
    ModeValue mode = new ModeValue("Bypass Mode" , Mode.New , Mode.values());

    int boostMotion = 0;
    DoubleValue BoostSpeed = new DoubleValue("BoostSpeed" , 3 , 1 ,2 ,0.1);
    DoubleValue JumpTimer = new DoubleValue("JumpTimer" , 2 , 0.1 , 0.1 , 0.1);
    DoubleValue BoostTimer = new DoubleValue("BoostTimer" ,3 , 0.5 , 1 , 0.1);
    public MTest(){
        super("MTest", Category.Movement);
        addValues(mode , BoostSpeed , JumpTimer , BoostTimer);
    }

    @Override
    public void onEnable() {
        boostMotion = 0;
        super.onEnable();
    }

    @Listener
    public void onUpdate(EventUpdate e){
        mc.getThePlayer().setNoClip(false);
        if (boostMotion == 0) {
            double yaw = Math.toRadians(mc.getThePlayer().getRotationYaw());
            mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(
                    mc.getThePlayer().getPosX(),
                    mc.getThePlayer().getPosY(),
                    mc.getThePlayer().getPosZ(),
                    true
            ));

            if (mode.getValue() == Mode.High) {
                MovementUtils.strafe(5.0f);
                mc.getThePlayer().setMotionY(2.0);
            } else {
                mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(
                        mc.getThePlayer().getPosX() - Math.sin(yaw) * 1.5,
                        mc.getThePlayer().getPosY() + 1,
                        mc.getThePlayer().getPosZ() + Math.cos(yaw) * 1.5,
                        false
                ));
            }

            boostMotion = 1;
            mc.getTimer().setTimerSpeed(JumpTimer.getValue().floatValue());
        } else if (boostMotion == 1 && mode.getValue() == Mode.High) {
            MovementUtils.strafe(1.89f);
            mc.getThePlayer().setMotionY(2.0);
        } else if (boostMotion == 2) {
            MovementUtils.strafe(BoostSpeed.getValue().floatValue());

            if (mode.getValue() == Mode.Stable) {
                mc.getThePlayer().setMotionY(0.8);
            }

            if (mode.getValue() == Mode.New) {
                mc.getThePlayer().setMotionY(0.48);
            }

            if (mode.getValue() == Mode.High) {
                double yaw = Math.toRadians(mc.getThePlayer().getRotationYaw());
                mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(
                        mc.getThePlayer().getPosX() - Math.sin(yaw) * 2,
                        mc.getThePlayer().getPosY() + 2.0,
                        mc.getThePlayer().getPosZ() + Math.cos(yaw) * 2,
                        true
                ));
                mc.getThePlayer().setMotionY(2);
                MovementUtils.strafe(1.89f);
            }
            boostMotion = 3;
        } else if (boostMotion < 5) {
            boostMotion++;
        } else if (boostMotion >= 5) {
            mc.getTimer().setTimerSpeed(BoostTimer.getValue().floatValue());
        }
    }

    @Listener
    public void onPacket(EventPacket e){
            if (mc.getCurrentScreen() == null && S08PacketPlayerPosLook.isS08PacketPlayerPosLook(e.getPacket())) {
                if (boostMotion == 1) {
                    boostMotion = 2;
                }
            }
    }

    enum Mode{
    New, Stable, High, Custom

    }
}
