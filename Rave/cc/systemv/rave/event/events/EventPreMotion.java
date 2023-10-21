package cc.systemv.rave.event.events;

import cc.systemv.rave.event.CancelableEvent;

public class EventPreMotion extends CancelableEvent {

    private double x;
    private double y;
    private double z;
    private float yaw;
    private final float originYaw;
    private float pitch;
    private final float originPitch;
    private boolean onGround;
    private boolean changed = false;

    public EventPreMotion(double x, double y, double z, float yaw, float pitch, boolean onGround){
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
    public float getYaw() {
        return yaw;
    }
    public float getPitch() {
        return pitch;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }
    public boolean isOnGround() {
        return onGround;
    }
    public boolean isValueChanged() {
        return changed;
    }
    public float getYawOld() {
        return originYaw;
    }
    public float getPitchOld() {
        return originPitch;
    }
}
