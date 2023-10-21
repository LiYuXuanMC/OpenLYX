package al.nya.reflect.utils.math;

import lombok.Getter;

public class Vector3d {
    @Getter private double x;
    @Getter private double y;
    @Getter private double z;
    public Vector3d(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
