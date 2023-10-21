package com.reflectmc.reflect.event.events.player;

import com.reflectmc.reflect.event.events.EventCancelable;
import com.reflectmc.reflect.obfuscate.ExportObfuscate;

public class EventMove extends EventCancelable {
    public double x;
    public double y;
    public double z;

    public EventMove(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @ExportObfuscate(name = "Move$getX")
    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }
    @ExportObfuscate(name = "Move$getY")
    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }
    @ExportObfuscate(name = "Move$getZ")
    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
