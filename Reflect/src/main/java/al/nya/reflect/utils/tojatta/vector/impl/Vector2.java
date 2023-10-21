package al.nya.reflect.utils.tojatta.vector.impl;


import al.nya.reflect.utils.tojatta.vector.Vector;

/**
 * Created by Tojatta on 12/17/2016.
 */
public class Vector2<T extends Number> extends Vector<Number> {

    public Vector2(T x, T y) {
        super(x, y, 0);
    }

    public Vector3<T> toVector3() {
        return new Vector3<>((T) getX(), ((T) getY()), ((T) getZ()));
    }
}
