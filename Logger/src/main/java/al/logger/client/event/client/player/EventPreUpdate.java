package al.logger.client.event.client.player;

import al.logger.client.event.Event;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import lombok.Getter;

public class EventPreUpdate implements Event {
    @Getter
    private double x;
    @Getter
    private double y;
    @Getter
    private double z;
    @Getter
    private float yaw;
    @Getter
    private final float originYaw;
    @Getter
    private float pitch;
    @Getter
    private final float originPitch;
    @Getter
    private boolean onGround;
    @Getter
    private boolean changed = false;

    public EventPreUpdate(double x,double y,double z,float yaw,float pitch,boolean onGround){
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.originYaw = yaw;
        this.pitch = pitch;
        this.originPitch = pitch;
        this.onGround = onGround;
    }
    public void setPosition(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.changed = true;
    }
    public void setX(double x){
        this.x = x;
        this.changed = true;
    }

    public void setY(double y){
        this.y = y;
        this.changed = true;
    }
    public void setZ(double z){
        this.z = z;
        this.changed = true;
    }
    public void setYaw(float yaw){
        this.yaw = yaw;
        this.changed = true;
    }
    public void setPitch(float pitch){
        this.pitch = pitch;
        this.changed = true;
    }
    public void setRotation(float yaw,float pitch){
        this.yaw = yaw;
        this.pitch = pitch;
        this.changed = true;
    }
    public void setOnGround(boolean onGround){
        this.onGround = onGround;
    }
    @ExportObfuscate(name = "getYaw")
    public float getYaw() {
        return yaw;
    }
    @ExportObfuscate(name = "getPitch")
    public float getPitch() {
        return pitch;
    }
    @ExportObfuscate(name = "getX")
    public double getX() {
        return x;
    }
    @ExportObfuscate(name = "getY")
    public double getY() {
        return y;
    }
    @ExportObfuscate(name = "getZ")
    public double getZ() {
        return z;
    }
    @ExportObfuscate(name = "isOnGround")
    public boolean isOnGround() {
        return onGround;
    }
    @ExportObfuscate(name = "isValueChanged")
    public boolean isValueChanged() {
        return changed;
    }
    @ExportObfuscate(name = "getYawOld")
    public float getYawOld() {
        return originYaw;
    }
    @ExportObfuscate(name = "getPitchOld")
    public float getPitchOld() {
        return originPitch;
    }
}
