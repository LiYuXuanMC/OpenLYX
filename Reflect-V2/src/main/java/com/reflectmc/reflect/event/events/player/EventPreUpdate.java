package com.reflectmc.reflect.event.events.player;

import com.reflectmc.reflect.obfuscate.ExportObfuscate;

public class EventPreUpdate extends EventLivingUpdate {
    private float yaw;
    private float pitch;
    private double x;
    private double y;
    private double z;
    private boolean onGround;
    private boolean valueChanged;
    private float yawOld;
    private float pitchOld;
    private boolean onGroundOld;
    public EventPreUpdate(float yaw,float pitch,double x,double y,double z,boolean onGround){
        this.yaw = yaw;
        this.pitch = pitch;
        this.x = x;
        this.y = y;
        this.z = z;
        this.onGround = onGround;
        this.valueChanged = false;
        this.yawOld = yaw;
        this.pitchOld = pitch;
        this.onGroundOld = onGround;
    }
    public void setOnGround(boolean b){
        onGround = b;
        valueChanged = true;
    }
    public void setRotation(float yaw,float pitch){
        this.yaw = yaw;
        this.pitch = pitch;
        this.valueChanged = true;
    }
    @ExportObfuscate(name = "Pre$getYaw")
    public float getYaw() {
        return yaw;
    }
    @ExportObfuscate(name = "Pre$getPitch")
    public float getPitch() {
        return pitch;
    }
    @ExportObfuscate(name = "Pre$getX")
    public double getX() {
        return x;
    }
    @ExportObfuscate(name = "Pre$getY")
    public double getY() {
        return y;
    }
    @ExportObfuscate(name = "Pre$getZ")
    public double getZ() {
        return z;
    }
    @ExportObfuscate(name = "Pre$isOnGround")
    public boolean isOnGround() {
        return onGround;
    }
    @ExportObfuscate(name = "Pre$isValueChanged")
    public boolean isValueChanged() {
        return valueChanged;
    }
    public void setValueChanged(boolean valueChanged) {
        this.valueChanged = valueChanged;
    }
    @ExportObfuscate(name = "Pre$getYawOld")
    public float getYawOld() {
        return yawOld;
    }
    @ExportObfuscate(name = "Pre$getPitchOld")
    public float getPitchOld() {
        return pitchOld;
    }
    @ExportObfuscate(name = "Pre$isOnGroundOld")
    public boolean isOnGroundOld() {
        return onGroundOld;
    }
}
