package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventMove;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.features.modules.Combat.KillAura;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.RotationUtils;
import al.nya.reflect.utils.entity.PlayerUtil;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderManager;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static al.nya.reflect.utils.MathHelper.sqrt;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class TargetStrafe extends Module {
    private ModeValue renderModeValue = new ModeValue("RenderMode",RenderMode.Circle,RenderMode.values());
    private OptionValue thirdPersonViewValue = new OptionValue("ThirdPersonView", false);
    DoubleValue radiusValue = new DoubleValue("Radius", 5D, 0.1D, 2D,"0.0");
    OptionValue holdSpaceValue = new OptionValue("HoldSpace", false);
    private OptionValue onlySpeedValue = new OptionValue("OnlySpeed", false);
    private OptionValue onlyflyValue = new OptionValue("keyFly", false);
    private int direction = -1;

    public TargetStrafe() {
        super("TargetStrafe", ModuleType.Movement);
        addValues(renderModeValue,thirdPersonViewValue,radiusValue,holdSpaceValue,onlySpeedValue,onlyflyValue);
    }
    @EventTarget
    public void onRender3D(EventRender3D event) {
        EntityLivingBase target = KillAura.curTarget;
        if (renderModeValue.getValue() != RenderMode.None && canStrafe(target)) {
            if (target == null || target.isNull()) return;
            //val counter = intArrayOf(0);
            RenderManager renderManager = mc.getRenderManager();
            if (renderModeValue.getValue() == RenderMode.Circle) {
                GL11.glPushMatrix();
                GL11.glDisable(3553);
                GL11.glEnable(2848);
                GL11.glEnable(2881);
                GL11.glEnable(2832);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glHint(3154, 4354);
                GL11.glHint(3155, 4354);
                GL11.glHint(3153, 4354);
                GL11.glDisable(2929);
                GL11.glDepthMask(false);
                GL11.glLineWidth(1.0f);
                GL11.glBegin(3);
                double x =
                        target.getLastTickPosX() + (target.getPosX() - target.getLastTickPosX()) * event.getPartialTicks() - renderManager.getViewerPosX();
                double y =
                        target.getLastTickPosY() + (target.getPosY() - target.getLastTickPosY()) * event.getPartialTicks() - renderManager.getViewerPosY();
                double z =
                        target.getLastTickPosZ() + (target.getPosZ() - target.getLastTickPosZ()) * event.getPartialTicks() - renderManager.getViewerPosZ();
                int ticksExisted =  mc.getThePlayer().getTicksExisted();
                for (int i = 0;i <= 359;i++){
                    Color rainbow = new Color(Color.HSBtoRGB((float) ((ticksExisted / 70.0 + sin(i / 50.0 * 1.75)) % 1.0f),0.7f,1f));
                    GL11.glColor3f(rainbow.getRed() / 255.0f, rainbow.getGreen() / 255.0f, rainbow.getBlue() / 255.0f);
                    GL11.glVertex3d(
                            x + radiusValue.getValue() * cos(i * 6.283185307179586 / 45.0),
                            y,
                            z + radiusValue.getValue() * sin(i * 6.283185307179586 / 45.0)
                    );
                }
                GL11.glEnd();
                GL11.glDepthMask(true);
                GL11.glEnable(2929);
                GL11.glDisable(2848);
                GL11.glDisable(2881);
                GL11.glEnable(2832);
                GL11.glEnable(3553);
                GL11.glPopMatrix();
            } else {
                //TODO: Skid it
                /*val rad = radiusValue.getValue();
                if (target == null || target.isNull()) {
                    return;
                }
                GL11.glPushMatrix();
                GL11.glDisable(3553);
                //RenderUtils.startDrawing()
                {
                    GL11.glEnable(3042);
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    GL11.glEnable(2848);
                    GL11.glDisable(3553);
                    GL11.glDisable(2929);
                    mc.getEntityRenderer().setupCameraTransform(mc.getTimer().getRenderPartialTicks(), 0);
                }
                GL11.glDisable(2929);
                GL11.glDepthMask(false);
                GL11.glLineWidth(1.0f);
                GL11.glBegin(3);
                double x =
                        target.getLastTickPosX() + (target.getPosX() - target.getLastTickPosX()) * event.getPartialTicks() - renderManager.getViewerPosX();
                double y =
                        target.getLastTickPosY() + (target.getPosY() - target.getLastTickPosY()) * event.getPartialTicks() - renderManager.getViewerPosY();
                double z =
                        target.getLastTickPosZ() + (target.getPosZ() - target.getLastTickPosZ()) * event.getPartialTicks() - renderManager.getViewerPosZ();
                for (int i = 0;i <= 10;i++) {
                    counter[0] = counter[0] + 1
                    val rainbow = Color(ColorManager.astolfoRainbow(counter[0] * 100, 5, 107))
                    //final Color rainbow = new Color(Color.HSBtoRGB((float) ((mc.thePlayer.ticksExisted / 70.0 + sin(i / 50.0 * 1.75)) % 1.0f), 0.7f, 1.0f));
                    GL11.glColor3f(rainbow.red / 255.0f, rainbow.green / 255.0f, rainbow.blue / 255.0f)
                    if (rad < 0.8 && rad > 0.0) GL11.glVertex3d(
                            x + rad * cos(i * 6.283185307179586 / 3.0),
                            y,
                            z + rad * sin(i * 6.283185307179586 / 3.0)
                    )
                    if (rad < 1.5 && rad > 0.7) {
                        counter[0] = counter[0] + 1
                        RenderUtils.glColor(ColorManager.astolfoRainbow(counter[0] * 100, 5, 107))
                        GL11.glVertex3d(
                                x + rad * cos(i * 6.283185307179586 / 4.0),
                                y,
                                z + rad * sin(i * 6.283185307179586 / 4.0)
                        )
                    }
                    if (rad < 2.0 && rad > 1.4) {
                        counter[0] = counter[0] + 1
                        RenderUtils.glColor(ColorManager.astolfoRainbow(counter[0] * 100, 5, 107))
                        GL11.glVertex3d(
                                x + rad * cos(i * 6.283185307179586 / 5.0),
                                y,
                                z + rad * sin(i * 6.283185307179586 / 5.0)
                        )
                    }
                    if (rad < 2.4 && rad > 1.9) {
                        counter[0] = counter[0] + 1
                        RenderUtils.glColor(ColorManager.astolfoRainbow(counter[0] * 100, 5, 107))
                        GL11.glVertex3d(
                                x + rad * cos(i * 6.283185307179586 / 6.0),
                                y,
                                z + rad * sin(i * 6.283185307179586 / 6.0)
                        )
                    }
                    if (rad < 2.7 && rad > 2.3) {
                        counter[0] = counter[0] + 1
                        RenderUtils.glColor(ColorManager.astolfoRainbow(counter[0] * 100, 5, 107))
                        GL11.glVertex3d(
                                x + rad * cos(i * 6.283185307179586 / 7.0),
                                y,
                                z + rad * sin(i * 6.283185307179586 / 7.0)
                        )
                    }
                    if (rad < 6.0 && rad > 2.6) {
                        counter[0] = counter[0] + 1
                        RenderUtils.glColor(ColorManager.astolfoRainbow(counter[0] * 100, 5, 107))
                        GL11.glVertex3d(
                                x + rad * cos(i * 6.283185307179586 / 8.0),
                                y,
                                z + rad * sin(i * 6.283185307179586 / 8.0)
                        )
                    }
                    if (rad < 7.0 && rad > 5.9) {
                        counter[0] = counter[0] + 1
                        RenderUtils.glColor(ColorManager.astolfoRainbow(counter[0] * 100, 5, 107))
                        GL11.glVertex3d(
                                x + rad * cos(i * 6.283185307179586 / 9.0),
                                y,
                                z + rad * sin(i * 6.283185307179586 / 9.0)
                        )
                    }
                    if (rad < 11.0) if (rad > 6.9) {
                        counter[0] = counter[0] + 1
                        RenderUtils.glColor(ColorManager.astolfoRainbow(counter[0] * 100, 5, 107))
                        GL11.glVertex3d(
                                x + rad * cos(i * 6.283185307179586 / 10.0),
                                y,
                                z + rad * sin(i * 6.283185307179586 / 10.0)
                        )
                    }
                }
                GL11.glEnd();
                GL11.glDepthMask(true);
                GL11.glEnable(2929);
                //RenderUtils.stopDrawing();
                {
                    GL11.glDisable(3042);
                    GL11.glEnable(3553);
                    GL11.glDisable(2848);
                    GL11.glDisable(3042);
                    GL11.glEnable(2929);
                }
                GL11.glEnable(3553);
                GL11.glPopMatrix();

                 */
            }
        }
    }
    /**
     *
     * @param move MoveEvent
     */
    @EventTarget
    public void onMove(EventMove move) {
        EntityLivingBase target = KillAura.curTarget;
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
        if (!PlayerUtil.isBlockUnder()) targetStrafe = 0f;

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
        float moveSpeed = sqrt(Math.pow(move.x,2.0) + Math.pow(move.z,2.0));

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
                && ((!onlySpeedValue.getValue() || ModuleManager.getModule(Speed.class).isEnable())
                || (onlyflyValue.getValue() && ModuleManager.getModule(Flight.class).isEnable()));
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

    public enum RenderMode {
        Circle,
        None,
        Pentagon
    }
}
