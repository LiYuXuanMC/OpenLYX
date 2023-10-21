/*
 * Decompiled with CFR 0_132.
 */
package al.nya.reflect.events.events;

import al.nya.reflect.events.annotation.Realized;
import lombok.Getter;
import lombok.Setter;

@Realized
public class EventPreUpdate
extends Event {
	private float yaw;
	private float pitch;
	private boolean ground;
	private float yawOld;
	private float pitchOld;
	private boolean groundOld;
	private boolean modified = false;
	@Getter
	@Setter
	private double x;
	@Getter
	@Setter
	private double y;
	@Getter
	@Setter
	private double z;
	@Getter
	private double xOld;
	@Getter
	private double yOld;
	@Getter
	private double zOld;
	public EventPreUpdate(float yaw, float pitch,double x,double y,double z,boolean ground) {
		this.yaw = yaw;
		this.pitch = pitch;
		this.ground = ground;
		this.yawOld = yaw;
		this.pitchOld = pitch;
		this.groundOld = ground;
		this.x = x;
		this.y = y;
		this.z = z;
		this.xOld = x;
		this.yOld = y;
		this.zOld = z;
	}
	public float getYaw() {
	        return yaw;
	    }
	public void setYaw(float yaw) {
		this.yaw = yaw;
		modified = true;
	}
	public float getPitch() {
		return pitch;
	}
	public void setPitch(float pitch) {
		this.pitch = pitch;
		modified = true;
	}
	public boolean isOnGround() {
		return this.ground;
	}
	public boolean isModified() {
		return modified;
	}
	public void setOnGround(boolean ground) {
	        this.ground = ground;
	    }
	public float getPitchOld() {
		return pitchOld;
	}
	public float getYawOld() {
		return yawOld;
	}
	public boolean isGroundOld() {
		return groundOld;
	}
}


