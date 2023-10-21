package al.logger.client.features.modules.impls.Movement;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventMove;
import al.logger.client.event.client.player.EventPlayerState;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.event.client.player.EventUpdate;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.utils.ChatUtils;
import al.logger.client.utils.player.MovementUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.ModeValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.KeyBinding;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.network.server.S12PacketEntityVelocity;
import al.logger.client.wrapper.LoggerMC.potion.Potion;
import al.logger.client.wrapper.LoggerMC.utils.MathHelper;
import org.lwjgl.input.Keyboard;

public class LongJump extends Module {

    int diff = 0;

    private final ModeValue mode = new ModeValue("Mode", Mode.SelfDamage,Mode.values());
    private final OptionValue stopMovement = new OptionValue("Stop Movement",true);
    private final DoubleValue waitingTicks = new DoubleValue("Waiting Ticks",20, 4, 10, 1);
    private final ModeValue horizontalMove = new ModeValue("Horizontal Move", HorizontalMoveMode.Ignore,HorizontalMoveMode.values());
    private final DoubleValue horizontalBoostAmount = new DoubleValue("Horizontal Boost Amount",1.0, 0.02, 0.2, 0.02);
    private final DoubleValue afterVelocityYBoost = new DoubleValue("After velocity Y boost",0.08, 0.0, 0.0, 0.002);
    private final OptionValue hypixelTimer = new OptionValue("Hypixel Timer", false);
    private boolean HH;
    private int bP;
    private int Dk;
    private double x,y,z;
    public LongJump(){
        super("LongJump", "LongJump", Category.Movement);
        addValues(mode,stopMovement,waitingTicks,horizontalMove,horizontalBoostAmount,afterVelocityYBoost,hypixelTimer);
        stopMovement.addCallBack(() -> mode.getValue() == Mode.SelfDamage);
        waitingTicks.addCallBack(() -> mode.getValue() == Mode.SelfDamage);
        horizontalMove.addCallBack(() -> mode.getValue() == Mode.SelfDamage);
        horizontalBoostAmount.addCallBack(() -> mode.getValue() == Mode.SelfDamage);
        afterVelocityYBoost.addCallBack(() -> mode.getValue() == Mode.SelfDamage);
        hypixelTimer.addCallBack(() -> mode.getValue() == Mode.SelfDamage);
    }

    @Override
    public void onEnable() {
        diff = 0;
        Logger.getInstance().getGuiManager().longJumpProgress.startLongJump(10);
        this.Dk = 0;
        this.bP = 0;
        this.HH = false;
        this.y = -1.0;
        if (mode.getValue() == Mode.SelfDamage){
            if (!mc.getThePlayer().isOnGround()) {
                this.bP = 3;
                return;
            }
        }
    }

    @Listener
    public void onTick(EventTick event) {
        diff++;
        Logger.getInstance().getGuiManager().longJumpProgress.updateLongJump(diff);
    }

    @Override
    public void onDisable() {
        Logger.getInstance().getGuiManager().longJumpProgress.stopLongJump();
    }
    @Listener
    public void onUpdate(final EventUpdate oc) {
        if (mode.getValue() == Mode.SelfDamage){
            EntityPlayerSP thePlayer = mc.getThePlayer();
            if (this.HH) {
                ++this.Dk;
                if (this.Dk == this.waitingTicks.getValue() && this.y != -1.0) {
                    if (horizontalMove.getValue().equals(HorizontalMoveMode.Legit)) {
                        thePlayer.setMotionX(x);
                        thePlayer.setMotionZ(z);
                        thePlayer.setMotionX(thePlayer.getMotionX() + MathHelper.sin((float) Math.toRadians(thePlayer.getRotationYaw())) * this.horizontalBoostAmount.getValue());
                        thePlayer.setMotionZ(thePlayer.getMotionZ() + MathHelper.cos((float) Math.toRadians(thePlayer.getRotationYaw())) * this.horizontalBoostAmount.getValue());
                    } else if (horizontalMove.getValue().equals(HorizontalMoveMode.Boost)) {
                        thePlayer.setMotionX(thePlayer.getMotionX() + MathHelper.sin((float) Math.toRadians(thePlayer.getRotationYaw())) * this.horizontalBoostAmount.getValue());
                        thePlayer.setMotionZ(thePlayer.getMotionZ() + MathHelper.cos((float) Math.toRadians(thePlayer.getRotationYaw())) * this.horizontalBoostAmount.getValue());
                    }
                    thePlayer.setMotionX(x);
                }
                if (this.Dk > this.waitingTicks.getValue() && this.y != -1.0) {
                    thePlayer.setMotionY(thePlayer.getMotionY() + this.afterVelocityYBoost.getValue());
                }
                if (this.hypixelTimer.getValue()) {
                    mc.getTimer().setTimerSpeed(1.0f);
                }
                if (this.Dk > 2 && this.Dk < this.waitingTicks.getValue()) {
                    //Vestige_Main.INSTANCE.Gl().ui();
                }
                else {
                    //Vestige_Main.INSTANCE.Gl().Gf();
                }
                if (thePlayer.isOnGround()) {
                    this.toggle();
                    return;
                }
                return;
            }
            else {
                if (thePlayer.isOnGround()) {
                    thePlayer.jump();
                    ++this.bP;
                    if (this.bP > 3) {
                        this.HH = true;
                        KeyBinding keyBindForward = mc.getGameSettings().getKeyBindForward();
                        keyBindForward.setPressed(Keyboard.isKeyDown(keyBindForward.getKeyCode()));
                        return;
                    }
                }
                if (thePlayer.getMotionY() > 0.3) {
                    //Vestige_Main.INSTANCE.Gl().Gf();
                    if (this.hypixelTimer.getValue()) {
                        mc.getTimer().setTimerSpeed(1.0f);
                        return;
                    }
                    return;
                }
                else {
                    //Vestige_Main.INSTANCE.Gl().ui();
                    if (this.hypixelTimer.getValue()) {
                        mc.getTimer().setTimerSpeed(((this.bP < 3) ? 5.0f : 2.0f));
                        return;
                    }
                    return;
                }
            }
        }
    }
    @Listener
    public void onState(final EventPlayerState ax) {
        if (this.mode.getValue() == Mode.SelfDamage && this.stopMovement.getValue() && this.bP < 4) {
            ax.setSprint(false);
        }
    }
    @Listener
    public void onMove(final EventMove eventMove) {
        if (mode.getValue() == Mode.SelfDamage){
            if (this.stopMovement.getValue()) {
                EntityPlayerSP thePlayer = mc.getThePlayer();
                if (this.bP <= 3) {
                    MovementUtils.setSpeed(eventMove, 0.0);
                }
                else if (this.bP == 4 && thePlayer.isOnGround() && eventMove.getY() > 0.4) {
                    if (thePlayer.isPotionActive(Potion.moveSpeed)) {
                        MovementUtils.setSpeed(eventMove, 0.6 + Y() * 0.07);
                    }
                    else {
                        MovementUtils.setSpeed(eventMove, 0.6);
                    }
                }
            }
            if (!this.HH || this.horizontalMove.getValue() != HorizontalMoveMode.Verus) {
                return;
            }
            if (this.Dk >= this.waitingTicks.getValue() + 14) {
                MovementUtils.setSpeed(eventMove, 0.28);
                return;
            }
            if (this.Dk >= this.waitingTicks.getValue()) {
                MovementUtils.setSpeed(eventMove, 9.0);
                return;
            }
            return;
        }
    }
    public int Y() {
        if (mc.getThePlayer().isPotionActive(Potion.moveSpeed)) {
            return 1 + mc.getThePlayer().getActivePotionEffect(Potion.moveSpeed).getAmplifier();
        }
        return 0;
    }
    @Listener
    public void onPreUpdate(final EventPreUpdate preUpdate) {
        if (mode.getValue() == Mode.SelfDamage){
            if (!this.HH && this.bP < 3) {
                preUpdate.setOnGround(false);
                return;
            }
            return;
        }
    }
    @Listener
    public void onPacket(EventPacket eventPacket){
        if (mode.getValue() == Mode.SelfDamage){
            if (!(S12PacketEntityVelocity.isS12PacketEntityVelocity(eventPacket.getPacket()))) {
                return;
            }
            final S12PacketEntityVelocity s12PacketEntityVelocity = new S12PacketEntityVelocity(eventPacket.getPacket().getWrappedObject());
            if (s12PacketEntityVelocity.getEntityID() == mc.getThePlayer().getEntityId()) {
                eventPacket.cancel();
                this.x = s12PacketEntityVelocity.getMotionX() / 8000.0;
                this.y = s12PacketEntityVelocity.getMotionY() / 8000.0;
                this.z = s12PacketEntityVelocity.getMotionZ() / 8000.0;
                ChatUtils.message("Velocity Y : " + this.y);
                return;
            }
            return;
        }
    }
    enum Mode {
        SelfDamage
    }
    enum HorizontalMoveMode {
        Legit,
        Ignore,
        Boost,
        Verus
    }
}
