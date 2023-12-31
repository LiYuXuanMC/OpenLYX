package al.logger.client.utils.rotation;

import al.logger.client.wrapper.LoggerMC.Minecraft;
import al.logger.client.wrapper.LoggerMC.entity.EntityPlayer;

public class Rotation {
    private float yaw;
    private float pitch;
    public Rotation(float yaw, float pitch){
        this.yaw = yaw;
        this.pitch = pitch;
    }

    /**
     * Set rotations to [player]
     */
    public void toPlayer(EntityPlayer player) {
        Minecraft mc = Minecraft.getMinecraft();
        if (yaw == 0 || pitch == 0)
            return;

        fixedSensitivity(mc.getGameSettings().getMouseSensitivity());

        player.setPrevRotationYaw(yaw);
        player.setPrevRotationPitch(pitch);
    }

    public void fixedSensitivity(float sensitivity) {
        float f = sensitivity * 0.6F + 0.2F;
        float gcd = f * f * f * 1.2F;

        // get previous rotation
        Rotation rotation = RotationUtils.serverRotation;

        // fix yaw
        float deltaYaw = yaw - rotation.yaw;
        deltaYaw -= deltaYaw % gcd;
        yaw = rotation.yaw + deltaYaw;

        // fix pitch
        float deltaPitch = pitch - rotation.pitch;
        deltaPitch -= deltaPitch % gcd;
        pitch = rotation.pitch + deltaPitch;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float[] getValue(){
        return new float[]{yaw,pitch};
    }
}