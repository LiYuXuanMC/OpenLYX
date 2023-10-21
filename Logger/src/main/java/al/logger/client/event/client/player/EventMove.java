package al.logger.client.event.client.player;

import al.logger.client.event.CancelableEvent;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;

public class EventMove extends CancelableEvent {
    public double x;
    public double y;
    public double z;

    public EventMove(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @ExportObfuscate(name = "getX")
    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }
    @ExportObfuscate(name = "getY")
    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }
    @ExportObfuscate(name = "getZ")
    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
