package al.nya.reflect.features.modules.Visual;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.features.modules.Combat.BowAimBot;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.render.ColorUtils;
import al.nya.reflect.utils.render.RenderUtil;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.block.IBlockState;
import al.nya.reflect.wrapper.wraps.wrapper.block.Material;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.item.*;
import al.nya.reflect.wrapper.wraps.wrapper.render.DefaultVertexFormats;
import al.nya.reflect.wrapper.wraps.wrapper.render.RenderManager;
import al.nya.reflect.wrapper.wraps.wrapper.render.Tessellator;
import al.nya.reflect.wrapper.wraps.wrapper.render.WorldRenderer;
import al.nya.reflect.wrapper.wraps.wrapper.utils.AxisAlignedBB;
import al.nya.reflect.wrapper.wraps.wrapper.utils.BlockPos;
import al.nya.reflect.wrapper.wraps.wrapper.utils.MovingObjectPosition.MovingObjectPosition;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * From LiquidBounce
 */
public class Projectiles extends Module {
    public ModeValue colorMode = new ModeValue("Color",ColorMode.BowPower,ColorMode.values());
    public OptionValue detectHit  = new OptionValue("DetectHit",true);
    public Projectiles() {
        super("Projectiles",ModuleType.Visual);
        addValues(colorMode,detectHit);
    }
    @Override
    public String getSuffix(){
        return colorMode.getValue().name();
    }
    @EventTarget
    public void onRender3D(EventRender3D render3D){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        WorldClient theWorld = mc.getTheWorld();
        ItemStack heldItem = thePlayer.getHeldItem();
        if (heldItem.isNull())return;
        Item item = heldItem.getItem();
        RenderManager renderManager = mc.getRenderManager();
        boolean isBow = false;
        float motionFactor = 1.5F;
        float motionSlowdown = 0.99F;
        float gravity;
        float size;
        boolean potion = false;
        if (ItemBow.isItemBow(item)) {
            if (!thePlayer.isUsingItem()) return;
            isBow = true;
            gravity = 0.05F;
            size = 0.3F;
            float power = thePlayer.getItemInUseDuration() / 20f;
            power = (power * power + power * 2F) / 3F;
            if (power < 0.1F) return;
            if (power > 1F)
                power = 1F;
            motionFactor = power * 3F;
        }else if (ItemFishingRod.isItemFishingRod(item)) {
            gravity = 0.04F;
            size = 0.25F;
            motionSlowdown = 0.92F;
        }else if (ItemPotion.isItemPotion(item) && ItemPotion.isSplash(heldItem.getItemDamage())) {
            gravity = 0.05F;
            size = 0.25F;
            motionFactor = 0.5F;
            potion = true;
        } else {
            if (!ItemSnowball.isItemSnowball(item) && !ItemEnderPearl.isItemEnderPearl(item) && !ItemEgg.isItemEgg(item)) return ;
            gravity = 0.03F;
            size = 0.25F;
        }
        float yaw = BowAimBot.getYaw();
        float pitch = BowAimBot.getPitch();
        float yawRadians = yaw / 180f * (float) Math.PI;
        float pitchRadians = pitch / 180f * (float) Math.PI;
        double renderPosX = renderManager.getRenderPosX();
        double renderPosY = renderManager.getRenderPosY();
        double renderPosZ = renderManager.getRenderPosZ();
        // Positions
        double posX = renderPosX - Math.cos(yawRadians) * 0.16F;
        double posY = renderPosY + thePlayer.getEyeHeight() - 0.10000000149011612;
        double posZ = renderPosZ - Math.sin(yawRadians) * 0.16F;

        double motionX = (-Math.sin(yawRadians) * Math.cos(pitchRadians) * (isBow ? 1.0 : 4.0));
        double motionY = -Math.sin((pitch + (potion ? -20 : 0)) / 180f * 3.1415927f) * (isBow ? 1.0 : 0.4);
        double motionZ = (Math.cos(yawRadians) * Math.cos(pitchRadians) * (isBow ? 1.0 : 4.0));
        double distance = Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);

        motionX /= distance;
        motionY /= distance;
        motionZ /= distance;
        motionX *= motionFactor;
        motionY *= motionFactor;
        motionZ *= motionFactor;

        MovingObjectPosition landingPosition = null;
        boolean hasLanded = false;
        boolean hitEntity = false;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GL11.glDepthMask(false);
        RenderUtil.enableGlCap(GL11.GL_BLEND, GL11.GL_LINE_SMOOTH);
        RenderUtil.disableGlCap(GL11.GL_DEPTH_TEST, GL11.GL_ALPHA_TEST, GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
        ColorMode mode = (ColorMode) colorMode.getValue();
        if (mode == ColorMode.BowPower){
            RenderUtil.glColor(interpolateHSB(Color.RED, Color.GREEN, (motionFactor / 30) * 10));
        }else if (mode == ColorMode.RainBow){
            RenderUtil.glColor(ColorUtils.rainbow(10,0,1F));
        }
        GL11.glLineWidth(2f);
        worldRenderer.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
        while (!hasLanded && posY > 0.0) {
            // Set pos before and after
            Vec3 posBefore = new Vec3(posX, posY, posZ);
            Vec3 posAfter = new Vec3(posX + motionX, posY + motionY, posZ + motionZ);

            // Get landing position
            landingPosition = theWorld.rayTraceBlocks(posBefore, posAfter, false, true, false);

            // Set pos before and after
            posBefore =new Vec3(posX, posY, posZ);
            posAfter =new Vec3(posX + motionX, posY + motionY, posZ + motionZ);

            // Check if arrow is landing
            if (landingPosition != null && !landingPosition.isNull()) {
                hasLanded = true;
                Vec3 hitVec = landingPosition.getHitVec();
                posAfter = new Vec3(hitVec.getXCoord(), hitVec.getYCoord(), hitVec.getZCoord());
            }
            if (detectHit.getValue()){
                // Set arrow box
                AxisAlignedBB arrowBox = new AxisAlignedBB(posX - size, posY - size, posZ - size, posX + size,
                        posY + size, posZ + size).addCoord(motionX, motionY, motionZ).expand(1.0, 1.0, 1.0);

                int chunkMinX = (int) Math.floor((arrowBox.getMinX() - 2.0) / 16.0);
                int chunkMaxX = (int) Math.floor((arrowBox.getMaxX() + 2.0) / 16.0);
                int chunkMinZ = (int) Math.floor((arrowBox.getMinZ() - 2.0) / 16.0);
                int chunkMaxZ = (int) Math.floor((arrowBox.getMaxZ() + 2.0) / 16.0);

                // Check which entities colliding with the arrow
                ArrayList<Entity> collidedEntities = new ArrayList<Entity>();
                for (int x = chunkMinX; x < chunkMaxX; x ++) {
                    for (int z = chunkMinZ; z < chunkMaxZ; z ++) {
                        theWorld.getChunkFromChunkCoords(x, z)
                                .getEntitiesWithinAABBForEntity(thePlayer, arrowBox, collidedEntities, null);
                    }
                }
                //ClientUtil.printChat(ClientUtil.Level.DEBUG,"Collided X"+chunkMinX+"-"+chunkMaxX+" Z"+chunkMinZ+"-"+chunkMaxZ+" collidedEntities "+collidedEntities.size());
                // Check all possible entities
                for (Entity possibleEntity : collidedEntities) {
                    if (possibleEntity.canBeCollidedWith() && !possibleEntity.equals(thePlayer)) {
                        AxisAlignedBB possibleEntityBoundingBox = possibleEntity.getEntityBoundingBox()
                                .expand(size, size, size);

                        MovingObjectPosition possibleEntityLanding = possibleEntityBoundingBox
                                .calculateIntercept(posBefore, posAfter);
                        if (!possibleEntityLanding.isNull()) {
                            hitEntity = true;
                            hasLanded = true;
                            landingPosition = possibleEntityLanding;
                            //ClientUtil.printChat(ClientUtil.Level.DEBUG,"Landed");
                        }
                    }
                }
            }

            // Affect motions of arrow
            posX += motionX;
            posY += motionY;
            posZ += motionZ;

            IBlockState blockState = theWorld.getBlockState(new BlockPos(posX, posY, posZ));

            // Check is next position water
            if (Objects.equals(blockState.getBlock().getMaterial(), Material.getWater())) {
                // Update motion
                motionX *= 0.6;
                motionY *= 0.6;
                motionZ *= 0.6;
            } else { // Update motion
                motionX *= motionSlowdown;
                motionY *= motionSlowdown;
                motionZ *= motionSlowdown;
            }

            motionY -= gravity;

            // Draw path
            worldRenderer.pos(posX - renderPosX, posY - renderPosY,
                    posZ - renderPosZ).endVertex();
        }
        tessellator.draw();
        GL11.glPushMatrix();
        GL11.glTranslated(posX - renderPosX, posY - renderPosY,
                posZ - renderPosZ);

        if (landingPosition != null && !landingPosition.isNull()) {
            // Switch rotation of hit cylinder of the hit axis
            int ordinal = landingPosition.getSideHit().ordinal();
            if (ordinal == 0){
                GL11.glRotatef(90F, 0F, 0F, 1F);
            }else if (ordinal == 2){
                GL11.glRotatef(90F, 1F, 0F, 0F);
            }

            // Check if hitting a entity
            if (hitEntity)
                RenderUtil.glColor(new Color(255, 0, 0, 150));
        }

        // Rendering hit cylinder
        GL11.glRotatef(-90F, 1F, 0F, 0F);

        Cylinder cylinder = new Cylinder();
        cylinder.setDrawStyle(GLU.GLU_LINE);
        cylinder.draw(0.2F, 0F, 0F, 60, 1);

        GL11.glPopMatrix();
        GL11.glDepthMask(true);
        RenderUtil.resetCaps();
        GL11.glColor4f(1F, 1F, 1F, 1F);
    }
    public Color interpolateHSB(Color startColor,Color endColor,float process){
        float[] startHSB = Color.RGBtoHSB(startColor.getRed(), startColor.getGreen(), startColor.getBlue(), null);
        float[] endHSB = Color.RGBtoHSB(endColor.getRed(), endColor.getGreen(), endColor.getBlue(), null);
        float brightness = (startHSB[2] + endHSB[2]) / 2;
        float saturation = (startHSB[1] + endHSB[1]) / 2;
        float hueMax = startHSB[0] > endHSB[0] ? startHSB[0] : endHSB[0];
        float hueMin = startHSB[0] > endHSB[0] ? endHSB[0] : startHSB[0];
        float hue = (hueMax - hueMin) * process + hueMin;
        return Color.getHSBColor(hue, saturation, brightness);
    }
    public enum ColorMode {
        RainBow,
        BowPower
    }
}
