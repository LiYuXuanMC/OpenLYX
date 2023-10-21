package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventRender2D;
import al.nya.reflect.features.modules.Ghost.Reach;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.World.GodBridgeHelper;
import al.nya.reflect.utils.LWJGLKeyBoard;
import al.nya.reflect.utils.LWJGLMouse;
import al.nya.reflect.utils.MathHelper;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.wrapper.wraps.wrapper.GameSettings;
import al.nya.reflect.wrapper.wraps.wrapper.entity.*;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import al.nya.reflect.wrapper.wraps.wrapper.item.Item;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.render.DefaultVertexFormats;
import al.nya.reflect.wrapper.wraps.wrapper.render.FontRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.render.Tessellator;
import al.nya.reflect.wrapper.wraps.wrapper.render.WorldRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectType;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;
import org.lwjgl.opengl.GL11;

public class MouseoverTips extends Module {
    private double tmp_global_alpha_multi = 1.0;

    public MouseoverTips() {
        super("MouseoverTips", "最佳瞄准度提示", ModuleType.Visual);
    }

    @EventTarget
    public void onRender2D(EventRender2D event) {
        Entity player = mc.getRenderViewEntity();
        GL11.glPushMatrix();

        GL11.glLoadIdentity();
        mc.getEntityRenderer().setupCameraTransform(event.getPartialTicks(), 0);
        double d0 = player.getLastTickPosX() + (player.getPosX() - player.getLastTickPosX()) * (double) event.getPartialTicks();
        double d1 = player.getLastTickPosY() + (player.getPosY() - player.getLastTickPosY()) * (double) event.getPartialTicks();
        double d2 = player.getLastTickPosZ() + (player.getPosZ() - player.getLastTickPosZ()) * (double) event.getPartialTicks();

        GL11.glTranslated( // Translate to player position with render pos and interpolate it
                -d0, -d1, -d2
        );
        renderGodBridgeHelper(event.getPartialTicks());
        renderMouseOver(mc.getFontRenderer(), event.getPartialTicks());
        GL11.glPopMatrix();


        mc.getEntityRenderer().setupOverlayRendering();


        GL11.glEnable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_DEPTH_TEST);

    }

    private void renderMiniMap(float partialTicks) {

//        GameSettings settings = mc.getGameSettings();
//
//
//        int keyCode = settings.getkeyBindPlayerList().getKeyCode();
//        boolean tab;
//        if (keyCode < 0) {
//            tab = LWJGLMouse.isButtonDown(keyCode + 100);
//        } else {
//            tab = LWJGLKeyBoard.isKeyDown(keyCode);
//        }
//
//        WorldClient world = mc.getTheWorld();
//        EntityPlayerSP thePlayer = mc.getThePlayer();
//
//        if (world.isNull() || thePlayer.isNull())
//            return;
//        GL11.glPushMatrix();
//        GL11.glLoadIdentity();
////        GL11.glTranslated(mc.displayHeight/2, mc.displayWidth/2, -2000.0);
//
//        ScaledResolution scaledResolution = new ScaledResolution(mc);
//        int width = setting.MINI_MAP_SIZE;
//        int height = setting.MINI_MAP_SIZE;
//        int scaleWidth = scaledResolution.getScaleFactor();
//        int scaleHeight = scaledResolution.getScaleFactor();
////        GL11.glTranslated(mc.getFramebuffer().framebufferWidth*scaleWidth/2, mc.getFramebuffer().framebufferHeight*scaleHeight/2, -2000.0);
//        GL11.glTranslated(mc.getFramebuffer().getFramebufferWidth() / scaleWidth - (width / 2) - 10,
//                mc.getFramebuffer().getFramebufferHeight() / scaleHeight - (height / 2) - 10, -2000.0);
//
//        RenderUtil.setColourWithAlphaPercent(0x000000, 50);
//        RenderUtil.drawRect(-width / 2, -height / 2, width, height);
//
//        GL11.glPushMatrix();
//        RenderUtil.setColourWithAlphaPercent(0x00AAFF, 100);
//        RenderUtil.drawCircle(0, 0, 1.6);
//        RenderUtil.drawRect(-0.7, -3.5, 1.4, 2.5);
//        GL11.glPopMatrix();
//
//        {// drawMap();
//            RenderUtil.setRectStencil(-width / 2, -height / 2, width, height);
//            GL11.glPushMatrix();
////            GL11.glLoadIdentity();
////            GL11.glRotated(((thePlayer.rotationYaw-180) * 0.017453292F), 0.0f, 0.0f, 1.0f);
//            GL11.glRotated(-(thePlayer.getRotationYaw() - 180), 0.0f, 0.0f, 1.0f);
////            Render.setCircularStencil(0, 0, this.mapMode.h / 2.0);
//
//            double scale = setting.MINI_MAP_SCALE;
//
//            EntityPlayerSP entityPlayerSP = thePlayer;
//
//            for (Entity entity : world.getLoadedEntityList()) {
//                if (entity instanceof EntityPlayerSP)
//                    continue;
//
//                if (!(EntityPlayer.isEntityPlayer(entity) || EntityFireball.isEntityFireball(entity)
//                        || IProjectile.isIProjectile(entity) || EntityAnimal.isEntityAnimal(entity)))
//                    continue;
//
//                double dx = entity.getLastTickPosX() + partialTicks * (entity.getPosX() - entity.getLastTickPosX())
//                        - entityPlayerSP.getPosX();
//                double dy = entity.getLastTickPosY() + partialTicks * (entity.getPosY() - entity.getLastTickPosY())
//                        - entityPlayerSP.getPosY();
//                double dz = entity.getLastTickPosZ() + partialTicks * (entity.getPosZ() - entity.getLastTickPosZ())
//                        - entityPlayerSP.getPosZ();
//
//                int color = 0x000000;
//                int alpha = 100;
//                float yaw = 0.0f;
//                boolean showFace = true;
//                if (EntityAnimal.isEntityAnimal(entity)) {
////                    EntityAnimal animal = new EntityAnimal(entity);
////                    if (this.farmDoor.isEntityConfirm(animal) || (tab && this.farmDoor.isEntityTaging(animal))) {
////                        color = 0xFF9900;
////                        yaw = entity.getRotationYawHead();
////                    } else {
////                        continue;
////                    }
//                } else if (entity instanceof EntityFireball || IProjectile.isIProjectile(entity)) {
////                	continue;
//                    if (IProjectile.isIProjectile(entity)) {
////                		continue loop;
//                        if (entity instanceof EntityArrow) {
//                            boolean inGround = new EntityArrow(entity.getWrapObject()).isGround();
//                            if (inGround)
//                                continue loop;
//                        }
//                        double motionX = entity.getMotionX();
//                        double motionZ = entity.getMotionZ();
//                        if (motionX * motionX + motionZ * motionZ < 0.01)
//                            continue loop;
//                        else
//                            yaw = (float) ((float) MathHelper.atan2(motionX, -motionZ) / 0.017453292F)
//                                    + 180;
//                        // LogManager.getLogger().log(Level.INFO, "motionX: " + entity.motionX + " ,
//                        // motionZ: " + entity.motionZ + " , yaw: " + yaw);
//                        color = 0xFFFF00;
//                    } else {
//                        double motionX = entity.getMotionX();
//                        double motionZ = entity.getMotionZ();
//                        if (entity.isOnGround())
//                            continue loop;
//                        if (motionX * motionX + motionZ * motionZ < 0.01)
//                            continue loop;
//                        else
//                            yaw = (float) ((float) MathHelper.atan2(motionX, -motionZ) / 0.017453292F)
//                                    + 180;
//                        // LogManager.getLogger().log(Level.INFO, "motionX: " + entity.motionX + " ,
//                        // motionZ: " + entity.motionZ + " , yaw: " + yaw);
//                        color = 0x00FFFF;
//                    }
//                } else {
//
//                    yaw = entity.getRotationYawHead();
//                    color = 0xff0000;
//                    if (EntityPlayer.isEntityPlayer(entity)) {
//                        EntityPlayer entityPlayer = new EntityPlayer(entity.getWrapObject());
//                        if (entityPlayer.isPlayerSleeping()) {
//                            alpha = 60;
//                        }
//                        ItemStack itemStack = entityPlayer.getInventory().getArmorInventory()[3];
//                        Item item = !itemStack.isNull() ? itemStack.getItem() : new Item(null);
//
//                        if (!item.isNull() && item.getRegistryName().equals("minecraft:tnt")) {
////    						Tool.logerror("item %s", item.getRegistryName());
//                            color = 0x00FF00;
//                        }
//                    }
//
//                    String text = entity.getDisplayName().getUnformattedText();
////                    // 第一个杀手
////                    if ((this.confirmMurderName == null && this.markMap.containsKey(text)
////                            && this.markMap.get(text).hasMurderItem)
////                            || (this.confirmMurderName != null
////                            && this.confirmMurderName.equals(((EntityPlayer) entity).getName()))) {
////                        color = 0xff00ff;
////                    }
////                    // 第二个杀手
////                    if ((this.confirmMurderNameDoubt == null && this.markMapDoubt.containsKey(text)
////                            && this.markMapDoubt.get(text).hasMurderItem)
////                            || (this.confirmMurderNameDoubt != null
////                            && this.confirmMurderNameDoubt.equals(((EntityPlayer) entity).getName()))) {
////                        color = 0xff00ff;
////                    }
//                }
//                GL11.glPushMatrix();
////                GL11.glLoadIdentity();
//                RenderUtil.setColourWithAlphaPercent(color, alpha);
//                GL11.glTranslated(dx * scale, dz * scale, 0);
//                GL11.glRotated(yaw - 180, 0.0f, 0.0f, 1.0f);
//                RenderUtil.drawCircle(0, 0, 1.5);
//                if (showFace)
//                    RenderUtil.drawRect(-0.7, -4.0, 1.4, 3.5);
//                GL11.glPopMatrix();
//            }
//
//            GL11.glPopMatrix();
//
//            RenderUtil.disableStencil();
//        }
//        // GL11.glTranslated(0,0,2000);
//        GL11.glPopMatrix();
//        GL11.glEnable(GL11.GL_DEPTH_TEST);
//        if (!this.mc.getCurrentScreen().isNull()) {
//            GL11.glEnable(GL11.GL_BLEND);
//        }
    }

    private void renderGodBridgeHelper(float partialTicks) {
        if (!ModuleManager.getModule(GodBridgeHelper.class).isEnable())
            return;

        if (mc.getTheWorld().isNull() || mc.getThePlayer().isNull())
            return;

        Entity entity = mc.getRenderViewEntity();

        if (EntityPlayerSP.isEntityPlayerSP(entity)) {
            Vec3 position = new Vec3(
                    partialTicks * (entity.getPosX() - entity.getPrevPosX()) + entity.getPrevPosX(),
                    partialTicks * (entity.getPosY() - entity.getPrevPosY()) + entity.getPrevPosY(),
                    partialTicks * (entity.getPosZ() - entity.getPrevPosZ()) + entity.getPrevPosZ());

            double board = 0.01;

            int alpha = (int) (0x33 * (1.0 - Math.abs(mc.getThePlayer().getRotationPitch() - 75) / 90));

            int color = 0x00000000 | alpha;
            if (ModuleManager.getModule(GodBridgeHelper.class).isGodBridgeHelperState()) {
                color = 0x00EE00AA;
            }
            drawBox(position, color, new Vec3(entity.getWidth(), 0.01, entity.getWidth()), new Vec3(0, 0, 0), board, false);

        }
    }

    private void renderMouseOver(FontRenderer fontRenderer, float partialTicks) {
        Entity entity = new Entity(null);
        Entity viewEntity = mc.getRenderViewEntity();
        double eye_distance;
        boolean fakeEntity = false;
        if (!mc.getObjectMouseOver().isNull() && mc.getObjectMouseOver().getTypeOfHit() == MovingObjectType.ENTITY) {
            entity = mc.getObjectMouseOver().getEntityHit();
            eye_distance = viewEntity.getPositionEyes(1.0f).distanceTo(mc.getObjectMouseOver().getHitVec());
        } else if (mc.getObjectMouseOver().isNull() || mc.getObjectMouseOver().getTypeOfHit() == MovingObjectType.MISS) {

            MovingObjectPosition objectMouseOver = mc.getObjectMouseOver();

//            double REACH_BLOCK = exampleMod.setting.REACH_BLOCK;
//            double REACH_ENTITY = exampleMod.setting.REACH_ENTITY;

//			exampleMod.setting.REACH_BLOCK = exampleMod.setting.REACH_BLOCK + 4.0;
//			exampleMod.setting.REACH_ENTITY = exampleMod.setting.REACH_ENTITY + 4.0;
//            ExampleMod.instance.tmp_reach_entity_addition = 4.0;
//
//            ExampleMod.instance.getMouseOver(partialTicks);

//			exampleMod.setting.REACH_BLOCK = REACH_BLOCK;
//			exampleMod.setting.REACH_ENTITY = REACH_ENTITY;
//            ExampleMod.instance.tmp_reach_entity_addition = 0.0;

            if (!mc.getObjectMouseOver().isNull() && mc.getObjectMouseOver().getTypeOfHit() == MovingObjectType.ENTITY) {
                entity = mc.getObjectMouseOver().getEntityHit();
                eye_distance = viewEntity.getPositionEyes(1.0f).distanceTo(mc.getObjectMouseOver().getHitVec());
            }

            mc.setObjectMouseOver(objectMouseOver);
            mc.getEntityRenderer().setPointedEntity(new Entity(null));


            tmp_global_alpha_multi = 0.3;
            fakeEntity = true;
        }

        if (!entity.isNull() && !viewEntity.isNull()) {


            Vec3 vec3 = viewEntity.getPositionEyes(1.0f);
            Vec3 vec31 = viewEntity.getLook(1.0f);
            Vec3 vec32 = vec3.addVector(vec31.getXCoord() * 10000, vec31.getYCoord() * 10000, vec31.getZCoord() * 10000);

            float f1 = entity.getCollisionBorderSize();
            AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().expand(f1, f1,
                    f1);
            MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

            boolean isOut = false;

            if (axisalignedbb.isVecInside(vec3)) {
                eye_distance = 0;
            } else if (!movingobjectposition.isNull()) {
//				Tool.log("eye_distance fix %s to %s", eye_distance, viewEntity.getPositionEyes(1.0f).distanceTo(movingobjectposition.hitVec));

                eye_distance = viewEntity.getPositionEyes(1.0f).distanceTo(movingobjectposition.getHitVec());
            } else {
                eye_distance = viewEntity.getDistanceToEntity(entity);
                isOut = true;
            }

            Vec3 position = new Vec3(
                    partialTicks * (entity.getPosX() - entity.getPrevPosX()) + entity.getPrevPosX(),
                    partialTicks * (entity.getPosY() - entity.getPrevPosY()) + entity.getPrevPosY(),
                    partialTicks * (entity.getPosZ() - entity.getPrevPosZ()) + entity.getPrevPosZ());

            double distance = viewEntity.getDistance(position.getXCoord(), position.getYCoord(), position.getZCoord()) / 4F;

            double board = Math.min(distance, 512) / 100F * 2 + Math.max(distance - 1.5, 0) / 100F * 3.0;

            int color = 0xFFFFFFA0;
            if (EntityLivingBase.isEntityLivingBase(entity) && (new EntityLivingBase(entity.getWrapObject())).getHurtTime() > 0) {
                color = 0xFF0000CC;
            }
            drawBox(position, color, new Vec3(entity.getWidth(), entity.getHeight(), entity.getWidth()), new Vec3(0, 0.5 * entity.getHeight(), 0), board);

            Entity entity1 = entity;
            f1 = entity1.getCollisionBorderSize();
//            if (EntityPlayer.isEntityPlayer(entity1))
//                f1 += exampleMod.setting.VALUE_HIT_BOX_EXPAND;
//            else if (exampleMod.farmDoor.enableMark && exampleMod.farmDoor.isEntityConfirm(entity1))
//                f1 += exampleMod.setting.VALUE_HIT_BOX_EXPAND_FARM_DOOR;

            color = 0xBBBBBBA0;
            if (EntityLivingBase.isEntityLivingBase(entity) && (new EntityLivingBase(entity.getWrapObject())).getHurtTime() > 0) {
                color = 0xCC0000CC;
            }
            drawBox(position, color, new Vec3(entity.getWidth() + 2 * f1, entity.getHeight() + 2 * f1, entity.getWidth() + 2 * f1), new Vec3(0, 0.5 * entity.getHeight(), 0), board);

            if (EntityPlayerSP.isEntityPlayerSP(viewEntity)) {

                double bottom = position.getYCoord() - f1;
                double top = position.getYCoord() + entity.getHeight() + f1;

                double eye_height = viewEntity.getPositionEyes(partialTicks).getYCoord();
                color = 0x0000AAA0;
                if (EntityLivingBase.isEntityLivingBase(entity) && (new EntityLivingBase(entity.getWrapObject())).getHurtTime() > 0) {
                    color = 0x0000CCCC;
                }

                double target_height = eye_height;

                Vec3 gizmo_size = new Vec3(board * 2 * 2, board * 4 * 2, board * 2 * 2);

                boolean inside = true;

                if (eye_height < bottom) {
                    target_height = bottom;
                    gizmo_size = new Vec3(board * 4 * 2, board * 2 * 2, board * 4 * 2);
                    inside = false;
                }

                if (eye_height > top) {
                    target_height = top;
                    gizmo_size = new Vec3(board * 4 * 2, board * 2 * 2, board * 4 * 2);
                    inside = false;
                }

                if (bottom < eye_height && eye_height < top) {
                    drawBox(position.add(new Vec3(0, target_height - position.getYCoord(), 0)), color, new Vec3(entity.getWidth() + 2 * f1, board, entity.getWidth() + 2 * f1), new Vec3(0, 0, 0), board);
                }

                color |= 0xFF0000FF;

                double x = viewEntity.getPositionEyes(partialTicks).getXCoord(), z = viewEntity.getPositionEyes(partialTicks).getZCoord();

                boolean inX = true, inZ = true;

                if (x < position.getXCoord() - entity.getWidth() * 0.5 - f1) {
                    x = position.getXCoord() - entity.getWidth() * 0.5 - f1;
                    inside = false;
                    inX = false;
                }

                if (x > position.getXCoord() + entity.getWidth() * 0.5 + f1) {
                    x = position.getXCoord() + entity.getWidth() * 0.5 + f1;
                    inside = false;
                    inX = false;
                }

                if (z < position.getZCoord() - entity.getWidth() * 0.5 - f1) {
                    z = position.getZCoord() - entity.getWidth() * 0.5 - f1;
                    inside = false;
                    inZ = false;
                }

                if (z > position.getZCoord() + entity.getWidth() * 0.5 + f1) {
                    z = position.getZCoord() + entity.getWidth() * 0.5 + f1;
                    inside = false;
                    inZ = false;
                }

                //				if (inX)
                //					x = MathHelper.clamp_double(x, position.xCoord - entity.getWidth()*0, position.xCoord + entity.getWidth()*0);
                //
                //				if (inZ)
                //					z = MathHelper.clamp_double(z, position.zCoord - entity.getWidth()*0, position.zCoord + entity.getWidth()*0);

                boolean attackKeyDown = mc.getGameSettings().getKeyBindLeft().isKeyDown();
                if (!inside) {
                    Vec3 targetVec3 = new Vec3(x, target_height, z);
                    drawBox(targetVec3, color, gizmo_size, new Vec3(0, 0, 0), board * 2);

                    double angle_range = 0;
                    if (attackKeyDown) {
                        EntityPlayer.isEntityPlayer(entity);
                        if (angle_range > 0) {

                            Vec3 lookVec3 = mc.getThePlayer().getLook(partialTicks);
                            Vec3 directionVec3 = targetVec3.subtract(mc.getThePlayer().getPositionEyes(partialTicks));

                            if (directionVec3.lengthVector() == 0) {

                            } else {
                                double angle = Math.acos(lookVec3.dotProduct(directionVec3) / lookVec3.lengthVector() / directionVec3.lengthVector()) / Math.PI * 180;
                                //Tool.log("angle %s", angle);
                                if (angle < angle_range) {
                                    Vec3 eyeVec3 = mc.getThePlayer().getPositionEyes(partialTicks);


                                    double d0 = targetVec3.getXCoord() - eyeVec3.getXCoord();
                                    double d1 = targetVec3.getYCoord() - eyeVec3.getYCoord();
                                    double d2 = targetVec3.getZCoord() - eyeVec3.getZCoord();

                                    //								Vec3 new_lobkVec3 = new Vec3(d0, d1, d2);

                                    //								AxisAlignedBB axisalignedbb2 = entity.getEntityBoundingBox().expand((double) f1, (double) f1,
                                    //										(double) f1);
                                    //								MovingObjectPosition movingobjectposition2 = axisalignedbb2.calculateIntercept(vec3, vec32);
                                    //
                                    ////								if ()

                                    double d3 = Math.sqrt(d0 * d0 + d2 * d2);
                                    float f = (float) (Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
                                    float ff1 = (float) (-(Math.atan2(d1, d3) * 180.0D / Math.PI));

                                    //								mc.thePlayer.setRotation(f, ff1);

                                    while (f < -180.0D) {
                                        f += 360.0F;
                                    }

                                    while (f >= 180.0D) {
                                        f -= 360.0F;
                                    }


                                    //						        suggestRotation_tmp = new Vec3(f, ff1, 0);

                                    mc.getThePlayer().setRotationYaw(f);
                                    mc.getThePlayer().setRotationPitch(ff1);

                                    mc.getThePlayer().setPrevRotationYaw(f);
                                    mc.getThePlayer().setPrevRotationPitch(ff1);


                                }
                            }


                        }
                    }
                }
            }

            if (isOut) {
                f1 = entity1.getCollisionBorderSize();
                drawBox(position, 0x0000AAAA, new Vec3(entity.getWidth() + 2 * f1, entity.getHeight() + 2 * f1, entity.getWidth() + 2 * f1), new Vec3(0, 0.5 * entity.getHeight(), 0), board);
            }


            double normal_distance = 3.0;
            if ((eye_distance > normal_distance + 0.005 - 1 || isOut)
                    && EntityPlayerSP.isEntityPlayerSP(viewEntity)
                    && mc.getPlayerController().gameIsSurvivalOrAdventure()) {

                GL11.glPushMatrix();

                GL11.glEnable(GL11.GL_BLEND);
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glDisable(GL11.GL_DEPTH_TEST);

                Vec3 position2 = entity.getPositionEyes(partialTicks);
                position2 = position2.add(new Vec3(0, -entity.getEyeHeight() + 0.5 * entity.getHeight(), 0));


                f1 = entity.getWidth() + 0.2f;
                float angle = (float) (MathHelper.atan2(1, 1) / Math.PI * 180);
                float f = (viewEntity.getPrevRotationYaw() + partialTicks * (viewEntity.getPrevRotationYaw() - viewEntity.getRotationYaw()) - angle) * 0.017453292F;
                double motionX = -(double) (MathHelper.sin(f) * f1);
                double motionZ = MathHelper.cos(f) * f1;

                GL11.glTranslated(
                        position2.getXCoord() + motionX, position2.getYCoord(), position2.getZCoord() + motionZ
                );

                GL11.glRotatef(-mc.getRenderManager().getPlayerViewY(), 0F, 1F, 0F);
                GL11.glRotatef(mc.getRenderManager().getPlayerViewX(), 1F, 0F, 0F);

                double scaleMult = 4.0;

                double scale = Math.min(distance, 512) / 100F * scaleMult;
                GL11.glScaled(-scale, -scale, scale);


                double max_distance = Math.min(6.0, Reach.getAttackRange());
                double add_distance = eye_distance - normal_distance;
                String text = String.format("%.2f", add_distance);

                if (add_distance <= 0)
                    text = "OK " + text;
                else if (add_distance > 0) {
                    text = text + " L";
                }

                if ((int) Math.round(add_distance / 0.1) == 0) {
                    text = "OK";
                } else if (-1 <= add_distance && add_distance <= 0) {
                    int len = (int) Math.round(-add_distance / 0.1);
                    StringBuilder sb = new StringBuilder(len + 5);

                    sb.append("OK ");
                    for (int i = 0; i < len; i++) {
                        sb.append('|');
                    }
                    text = sb.toString();
                } else if (0 <= add_distance && add_distance <= 1) {
                    int len = (int) Math.round(add_distance / 0.1);
                    StringBuilder sb = new StringBuilder(len + 5);

                    for (int i = 0; i < len; i++) {
                        sb.append('|');
                    }
                    sb.append(" L");
                    text = sb.toString();

                }

                if (!(eye_distance > normal_distance + 0.005 - 1)) {
                    text = "isOut";
                }


                int width = fontRenderer.getStringWidth(text) / 2;

                RenderUtil.setColourWithAlphaPercent(0x000000, (int) (60 * (fakeEntity ? 0.5 : 1.0)));
                RenderUtil.drawRect(-width - 2F - width, -fontRenderer.getFontHeight() / 2 - 2, width + 4F - (-width - 2F), fontRenderer.getFontHeight() + 4);

                int r, g, b = 0, a = 255;
                r = (int) (255 * 1 * MathHelper.clamp_double(add_distance / (max_distance - normal_distance), 0, 1));
                g = 155 + (int) (100 * 1 * MathHelper.clamp_double((max_distance - normal_distance - add_distance) / (max_distance - normal_distance), 0, 1));
                b = isOut ? 0xFF : 0;
                a = (int) (255 * (fakeEntity ? 0.3 : 1.0));
                //g = 255;
//				Tool.log("g %s", g);
                GL11.glTranslated(
                        -width, 0, 0
                );
                RenderUtil.setColourWithAlphaPercent(0x000000, 100);
                fontRenderer.drawString(text, 1F + -width, -fontRenderer.getFontHeight() / 2,
                        a << 24 | r << 16 | g << 8 | b, true);

                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_BLEND);

                GL11.glPopMatrix();
            }


        }


        tmp_global_alpha_multi = 1.0;
    }

    private void drawBox(Vec3 position, int color, Vec3 size, Vec3 offset, double board) {
        drawBox(position, color, size, offset, board, true);
    }

    private void drawBox(Vec3 position, int color, Vec3 size, Vec3 offset, double board, boolean ignoreZTest) {
        GL11.glPushMatrix();

        GL11.glTranslated(
                position.getXCoord(),
                position.getYCoord(),
                position.getZCoord()
        );

        //GL11.glRotatef(-mc.getRenderManager().playerViewY, 0F, 1F, 0F);
        //GL11.glRotatef(mc.getRenderManager().playerViewX, 1F, 0F, 0F);

        RenderUtil.setColourWithAlphaPercent(color >> 8, (int) ((color & 0xFF) / 255.0 * 100 * tmp_global_alpha_multi));

        // Render.drawRect(-0.5, -0.5, 1 , 1);
        //double x=-0.5, y = -0.5, w = 1, h = 1;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        if (ignoreZTest)
            GL11.glDisable(GL11.GL_DEPTH_TEST);
        else
            GL11.glEnable(GL11.GL_DEPTH_TEST);

        Tessellator tes = Tessellator.getInstance();
//        tes.startDrawingQuads();
        WorldRenderer worlderRenderer = tes.getWorldRenderer();
        worlderRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);

        //     | y
        //     |
        //     |____ x
        //    /
        //   /z
        // Tool.log("spawn point %s", position);


        Vec3 global_scale = size; // 0.5 * 1.0

        double B = board;
        double BX = global_scale.getXCoord() > 0 ? B / global_scale.getXCoord() : 0.01;
        double BY = global_scale.getYCoord() > 0 ? B / global_scale.getYCoord() : 0.01;
        double BZ = global_scale.getZCoord() > 0 ? B / global_scale.getZCoord() : 0.01;


        global_scale = new Vec3(global_scale.getXCoord() * 0.5, global_scale.getYCoord() * 0.5, global_scale.getZCoord() * 0.5);
        Vec3 global_localPosition = offset;

        Vec3[][] quads = new Vec3[][]{
                new Vec3[]{new Vec3(1, -1, 1), new Vec3(-1, -1, 1), new Vec3(-1, 1, 1), new Vec3(1, 1, 1)}, // +z
                new Vec3[]{new Vec3(-1, -1, -1), new Vec3(1, -1, -1), new Vec3(1, 1, -1), new Vec3(-1, 1, -1)}, // -z
                new Vec3[]{new Vec3(1, -1, -1), new Vec3(1, -1, 1), new Vec3(1, 1, 1), new Vec3(1, 1, -1)}, // +x
                new Vec3[]{new Vec3(-1, -1, 1), new Vec3(-1, -1, -1), new Vec3(-1, 1, -1), new Vec3(-1, 1, 1)}, // -x
                new Vec3[]{new Vec3(-1, 1, 1), new Vec3(-1, 1, -1), new Vec3(1, 1, -1), new Vec3(1, 1, 1)}, // +y
                new Vec3[]{new Vec3(-1, -1, -1), new Vec3(-1, -1, 1), new Vec3(1, -1, 1), new Vec3(1, -1, -1)}, // -y
        };


        Vec3[][] lines = new Vec3[][]{
                // scale  // move position
                new Vec3[]{new Vec3(BX, BY, 1), new Vec3(1, 1, 0)},
                new Vec3[]{new Vec3(BX, BY, 1), new Vec3(-1, 1, 0)},
                new Vec3[]{new Vec3(BX, BY, 1), new Vec3(1, -1, 0)},
                new Vec3[]{new Vec3(BX, BY, 1), new Vec3(-1, -1, 0)},

                new Vec3[]{new Vec3(1, BY, BZ), new Vec3(0, 1, 1)},
                new Vec3[]{new Vec3(1, BY, BZ), new Vec3(0, -1, 1)},
                new Vec3[]{new Vec3(1, BY, BZ), new Vec3(0, 1, -1)},
                new Vec3[]{new Vec3(1, BY, BZ), new Vec3(0, -1, -1)},

                new Vec3[]{new Vec3(BX, 1, BZ), new Vec3(1, 0, 1)},
                new Vec3[]{new Vec3(BX, 1, BZ), new Vec3(-1, 0, 1)},
                new Vec3[]{new Vec3(BX, 1, BZ), new Vec3(1, 0, -1)},
                new Vec3[]{new Vec3(BX, 1, BZ), new Vec3(-1, 0, -1)},
        };

        for (int k = 0; k < lines.length; k++) {
            Vec3 scale = lines[k][0], localPosition = lines[k][1];
            for (int i = 0; i < quads.length; i++) {
                for (int j = 0; j < quads[i].length; j++) {
                    Vec3 vert = quads[i][j];
                    double vert_x = vert.getXCoord() * scale.getXCoord() + localPosition.getXCoord() * (1 - scale.getXCoord());
                    double vert_y = vert.getYCoord() * scale.getYCoord() + localPosition.getYCoord() * (1 - scale.getYCoord());
                    double vert_z = vert.getZCoord() * scale.getZCoord() + localPosition.getZCoord() * (1 - scale.getZCoord());
                    worlderRenderer.pos(
                            global_scale.getXCoord() * vert_x + global_localPosition.getXCoord(),
                            global_scale.getYCoord() * vert_y + global_localPosition.getYCoord(),
                            global_scale.getZCoord() * vert_z + global_localPosition.getZCoord()).endVertex();
                }
            }
        }


//			worlderRenderer.pos(x + w, y,     Render.zDepth).endVertex();
//		 	worlderRenderer.pos(x,     y,     Render.zDepth).endVertex();
//			worlderRenderer.pos(x,     y + h, Render.zDepth).endVertex();
//			worlderRenderer.pos(x + w, y + h, Render.zDepth).endVertex();

        tes.draw();

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix();
    }

}
