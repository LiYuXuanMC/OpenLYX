package al.nya.reflect.value;

import al.nya.reflect.utils.render.Translate;

public abstract class NumberValue<V extends Number> extends Value<V> {
    private V max;
    private V min;
    public Translate translate;
    public NumberValue(String name,V max,V min,V value) {
        super(name, value);
        this.max = max;
        this.min = min;
        this.translate = new Translate(0,0);
    }

    public V getMax() {
        return max;
    }

    public V getMin() {
        return min;
    }

    public V getValue(){
        return super.getValue();
    }
}
