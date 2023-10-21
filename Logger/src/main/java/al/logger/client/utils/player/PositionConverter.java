package al.logger.client.utils.player;

import al.logger.client.AccessInstance;
import al.logger.client.utils.misc.MathHelper;
import al.logger.client.wrapper.LoggerMC.entity.Entity;
import al.logger.client.wrapper.LoggerMC.gui.ScaledResolution;
import al.logger.client.wrapper.LoggerMC.render.GLAllocation;
import al.logger.client.wrapper.LoggerMC.utils.AxisAlignedBB;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.lwjgl.opengl.GL11.*;


public class PositionConverter implements AccessInstance {

    private static final FloatBuffer windPos = BufferUtils.createFloatBuffer(4);
    private static final IntBuffer intBuffer = GLAllocation.createDirectIntBuffer(16);
    private static final FloatBuffer floatBuffer1 = GLAllocation.createDirectFloatBuffer(16);
    private static final FloatBuffer floatBuffer2 = GLAllocation.createDirectFloatBuffer(16);

    public static double[] convertEntityPosition(Entity entity) {
        float renderPartialTicks = mc.getTimer().getRenderPartialTicks();
        double x = entity.getLastTickPosX() + (entity.getPosX() - entity.getLastTickPosX()) * renderPartialTicks - mc.getRenderManager().getViewerPosX();
        double y = entity.getLastTickPosY() + (entity.getPosY() - entity.getLastTickPosY()) * renderPartialTicks - mc.getRenderManager().getViewerPosY();
        double z = entity.getLastTickPosZ() + (entity.getPosZ() - entity.getLastTickPosZ()) * renderPartialTicks - mc.getRenderManager().getViewerPosZ();
        y += entity.getHeight() + 0.25d;
        if ((Objects.requireNonNull(convertTo2D(x, y, z))[2] >= 0.0D) && (Objects.requireNonNull(convertTo2D(x, y, z))[2] < 1.0D)) {
            double[] rotations = convertTo2D(x, y, z);
            if (rotations == null) {
                return new double[]{0.0, 0.0, 0.0, 0.0};
            }
            return new double[]{
                    rotations[0],
                    rotations[1],
                    Math.abs(convertTo2D(x, y + 1.0, z, entity)[1] - convertTo2D(x, y, z, entity)[1]),
                    rotations[2]
            };
        }
        return new double[]{0.0, 0.0, 0.0, 0.0};
    }

    public static Vector4f getEntityPositionsOn2D(Entity entity) {
        final double[] renderingEntityPos = getInterpolatedPos(entity);
        final double entityRenderWidth = entity.getWidth() / 1.5;
        final AxisAlignedBB bb = new AxisAlignedBB(renderingEntityPos[0] - entityRenderWidth,
                renderingEntityPos[1], renderingEntityPos[2] - entityRenderWidth, renderingEntityPos[0] + entityRenderWidth,
                renderingEntityPos[1] + entity.getHeight() + (entity.isSneaking() ? -0.3 : 0.18), renderingEntityPos[2] + entityRenderWidth).expand(0.15, 0.15, 0.15);


        final List<Vector3f> vectors = Arrays.asList(
                new Vector3f((float) bb.getMinX(), (float) bb.getMinY(), (float) bb.getMinZ()),
                new Vector3f((float) bb.getMinX(), (float) bb.getMaxY(), (float) bb.getMinZ()),
                new Vector3f((float) bb.getMaxX(), (float) bb.getMinY(), (float) bb.getMinZ()),
                new Vector3f((float) bb.getMaxX(), (float) bb.getMaxY(), (float) bb.getMinZ()),
                new Vector3f((float) bb.getMinX(), (float) bb.getMinY(), (float) bb.getMaxZ()),
                new Vector3f((float) bb.getMinX(), (float) bb.getMaxY(), (float) bb.getMaxZ()),
                new Vector3f((float) bb.getMaxX(), (float) bb.getMinY(), (float) bb.getMaxZ()),
                new Vector3f((float) bb.getMaxX(), (float) bb.getMaxY(), (float) bb.getMaxZ()));

        Vector4f entityPos = new Vector4f(Float.MAX_VALUE, Float.MAX_VALUE, -1.0f, -1.0f);
        ScaledResolution sr = new ScaledResolution(mc);
        for (Vector3f vector3f : vectors) {
            vector3f = projectOn2D(vector3f.x, vector3f.y, vector3f.z, sr.getScaleFactor());
            if (vector3f != null && vector3f.z >= 0.0 && vector3f.z < 1.0) {
                entityPos.x = Math.min(vector3f.x, entityPos.x);
                entityPos.y = Math.min(vector3f.y, entityPos.y);
                entityPos.z = Math.max(vector3f.x, entityPos.z);
                entityPos.w = Math.max(vector3f.y, entityPos.w);
            }
        }
        return entityPos;
    }

    public static double[] getInterpolatedPos(Entity entity) {
        float ticks = mc.getTimer().getRenderPartialTicks();
        return new double[]{
                MathHelper.interpolate(entity.getLastTickPosX(), entity.getPosX(), ticks) - mc.getRenderManager().getViewerPosX(),
                MathHelper.interpolate(entity.getLastTickPosY(), entity.getPosY(), ticks) - mc.getRenderManager().getViewerPosY(),
                MathHelper.interpolate(entity.getLastTickPosZ(), entity.getPosZ(), ticks) - mc.getRenderManager().getViewerPosZ()
        };
    }

    private static double[] convertTo2D(double x, double y, double z, Entity ent) {
        float pTicks = mc.getTimer().getRenderPartialTicks();
        float prevYaw = mc.getThePlayer().getRotationYaw();
        float prevPrevYaw = mc.getThePlayer().getPrevRotationYaw();
        float[] rotations = getRotationFromPosition(
                ent.getLastTickPosX() + (ent.getPosX() - ent.getLastTickPosX()) * pTicks,
                ent.getLastTickPosZ() + (ent.getPosZ() - ent.getLastTickPosZ()) * pTicks,
                ent.getLastTickPosY() + (ent.getPosY() - ent.getLastTickPosY()) * pTicks - 1.6D);
        mc.getRenderViewEntity().setRotationYaw(rotations[0]);
        mc.getRenderViewEntity().setPrevRotationYaw(rotations[0]);
        mc.getEntityRenderer().setupCameraTransform(pTicks, 0);
        double[] convertedPoints = convertTo2D(x, y, z);
        mc.getRenderViewEntity().setRotationYaw(prevYaw);
        mc.getRenderViewEntity().setPrevRotationYaw(prevPrevYaw);
        mc.getEntityRenderer().setupCameraTransform(pTicks, 0);
        return convertedPoints;
    }

    private static double[] convertTo2D(double x, double y, double z) {
        FloatBuffer screenCoords = BufferUtils.createFloatBuffer(3);
        IntBuffer viewport = BufferUtils.createIntBuffer(16);
        FloatBuffer modelView = BufferUtils.createFloatBuffer(16);
        FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        GL11.glGetFloat(2982, modelView);
        GL11.glGetFloat(2983, projection);
        GL11.glGetInteger(2978, viewport);
        boolean result = GLU.gluProject((float) x, (float) y, (float) z, modelView, projection, viewport, screenCoords);
        if (result) {
            return new double[]{screenCoords.get(0), Display.getHeight() - screenCoords.get(1), screenCoords.get(2)};
        }
        return null;
    }

    public static Vector3f projectOn2D(float x, float y, float z, int scaleFactor) {
        glGetFloat(GL_MODELVIEW_MATRIX, floatBuffer1);
        glGetFloat(GL_PROJECTION_MATRIX, floatBuffer2);
        glGetInteger(GL_VIEWPORT, intBuffer);
        if (GLU.gluProject(x, y, z, floatBuffer1, floatBuffer2, intBuffer, windPos)) {
            return new Vector3f(windPos.get(0) / scaleFactor, (mc.getDisplayHeight() - windPos.get(1)) / scaleFactor, windPos.get(2));
        }
        return null;
    }

    public static float[] getRotationFromPosition(double x, double z, double y) {
        double xDiff = x - mc.getThePlayer().getPosX();
        double zDiff = z - mc.getThePlayer().getPosZ();
        double yDiff = y - mc.getThePlayer().getPosY() - 1.2;
        double dist = MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
        float yaw = (float) (Math.atan2(zDiff, xDiff) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float) (-Math.atan2(yDiff, dist) * 180.0 / Math.PI);
        return new float[]{yaw, pitch};
    }

}
