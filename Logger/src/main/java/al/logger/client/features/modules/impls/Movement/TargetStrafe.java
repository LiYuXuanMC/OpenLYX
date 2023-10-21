package al.logger.client.features.modules.impls.Movement;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.player.EventMove;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.ModuleManager;
import al.logger.client.features.modules.impls.Combat.KillAura;
import al.logger.client.utils.player.PlayerUtils;
import al.logger.client.utils.rotation.RotationUtils;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.EntityLivingBase;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayerSP;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import al.logger.client.wrapper.LoggerMC.utils.MathHelper;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class TargetStrafe extends Module {
    private OptionValue thirdPersonViewValue = new OptionValue("ThirdPersonView", false);
    DoubleValue radiusValue = new DoubleValue("Radius", 5D, 0.1D, 2D,0.1);
    OptionValue holdSpaceValue = new OptionValue("HoldSpace", false);
    private OptionValue onlySpeedValue = new OptionValue("OnlySpeed", false);
    private OptionValue onlyflyValue = new OptionValue("keyFly", false);
    private OptionValue hurtTime = new OptionValue("HurtTime", false);
    private int direction = -1;

    public TargetStrafe() {
        super("TargetStrafe", Category.Movement);
        addValues(thirdPersonViewValue,radiusValue,holdSpaceValue,onlySpeedValue,onlyflyValue,hurtTime);
    }
    @Listener
    public void onMove(EventMove move) {
        EntityLivingBase target = KillAura.target;
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thirdPersonViewValue.getValue())
            mc.getGameSettings().setThirdPersonView(canStrafe(target) ? 3 : 0);
        if(!canStrafe(target)) return;
        boolean aroundVoid = false;
        for (int x = -1;x <= 0;x++){
            for (int z = -1;z <= 0;z++){
                if (isVoid(x, z)){
                    aroundVoid = true;
                }
            }
        }
        float yaw = RotationUtils.getRotationFromEyeHasPrev(target).getYaw();

        if (thePlayer.isCollidedHorizontally() || aroundVoid) direction *= -1;
        float moveStrafing = thePlayer.getMoveStrafing();
        float targetStrafe;
        if (moveStrafing != 0f) {
            targetStrafe = moveStrafing * direction;
        } else {
            targetStrafe = direction;
        }
        if (!PlayerUtils.isBlockUnder()) targetStrafe = 0f;

        float rotAssist = 45 / thePlayer.getDistanceToEntity(target);
        double moveAssist = (45f / getStrafeDistance(target));

        float mathStrafe = 0f;
        AxisAlignedBB targetBoundingBox = target.getEntityBoundingBox();
        AxisAlignedBB playerBoundingBox = thePlayer.getEntityBoundingBox();
        if (targetStrafe > 0) {
            if ((targetBoundingBox.getMinY() > playerBoundingBox.getMaxY() || targetBoundingBox.getMaxY() < playerBoundingBox.getMinY())
                    && thePlayer.getDistanceToEntity(target) < radiusValue.getValue())
                yaw += -rotAssist;
            mathStrafe += -moveAssist;
        } else if (targetStrafe < 0) {
            if ((targetBoundingBox.getMinY() > playerBoundingBox.getMaxY() || targetBoundingBox.getMaxY() < playerBoundingBox.getMinY())
                    && thePlayer.getDistanceToEntity(target) < radiusValue.getValue())
                yaw += rotAssist;
            mathStrafe += moveAssist;
        }
        double[] doSomeMath = new double[]{
                cos(Math.toRadians((yaw + 90f + mathStrafe))),
                sin(Math.toRadians((yaw + 90f + mathStrafe)))
        };
        float moveSpeed = MathHelper.sqrt_double(Math.pow(move.x,2.0) + Math.pow(move.z,2.0));

        double[] asLast = new double[]{
                moveSpeed * doSomeMath[0],
                moveSpeed * doSomeMath[1]
        };

        move.x = asLast[0];
        move.z = asLast[1];
        //        if (mc.thePlayer.isCollidedHorizontally || checkVoid()) direction = if (direction == 1) -1 else 1
//        if(checkVoid() && canStrafe) return
//        if (mc.gameSettings.keyBindLeft.isKeyDown) {
//            direction = 1
//        }
//        if (mc.gameSettings.keyBindRight.isKeyDown) {
//            direction = -1
//        }
//
//        if (!isVoid(0, 0) && canStrafe) {
//            MovementUtils.setSpeed(
//                event,
//                sqrt(event.x.pow(2.0) + event.z.pow(2.0)),
//                RotationUtils.toRotation(RotationUtils.getCenter(LiquidBounce.combatManager.target?.entityBoundingBox), true).yaw,
//                direction.toDouble(),
//                if (mc.thePlayer.getDistanceToEntity(LiquidBounce.combatManager.target) <= radiusValue.get()) 0.0 else 1.0
//            )
//        }
    }

    public boolean canStrafe(EntityLivingBase target){
        return ((target != null) && !target.isNull())
                && (!holdSpaceValue.getValue() || mc.getThePlayer().getMovementInput().isJump())
                && ((!onlySpeedValue.getValue() || Logger.getInstance().getModuleManager().getModule(Speed.class).isEnable())
                || (onlyflyValue.getValue() && Logger.getInstance().getModuleManager().getModule(Flight.class).isEnable()));
    }

    /*private boolean checkVoid(){
        for (x in -2..2) for (z in -2..2) if (isVoid(x, z)) return true
        return false
    }
     */

    private float getStrafeDistance(EntityLivingBase target){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        double a = (thePlayer.getDistanceToEntity(target) - radiusValue.getValue());
        double b = thePlayer.getDistanceToEntity(target) - (thePlayer.getDistanceToEntity(target) - radiusValue.getValue() / (radiusValue.getValue() * 2));
        if (b < a){
            return (float) a;
        }else {
            return (float) b;
        }
        /*return ((thePlayer.getDistanceToEntity(target) - radiusValue.getValue()).coerceAtLeast(
                thePlayer.getDistanceToEntity(target) - (thePlayer.getDistanceToEntity(target) - radiusValue.getValue() / (radiusValue.getValue() * 2))
        ));
         */
    }

    private boolean isVoid(int xPos,int zPos){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        double posY = thePlayer.getPosY();
        if (posY < 0.0) return true;
        int off = 0;
        AxisAlignedBB entityBoundingBox = thePlayer.getEntityBoundingBox();
        while (off < posY + 2) {
            AxisAlignedBB bb = entityBoundingBox.offset(xPos, -off, zPos);
            if (mc.getTheWorld().getCollidingBoundingBoxes(thePlayer, bb).isEmpty()) {
                off += 2;
                continue;
            }
            return false;
        }
        return true;
    }
}
