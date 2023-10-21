package al.logger.client.value.impls;


import al.logger.client.value.bases.Value;

public abstract class NumberValue<V extends Number> extends Value<V> {
    private V max;
    private V min;
    public NumberValue(String name,V max,V min,V value) {
        super(name, value);
        this.max = max;
        this.min = min;
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
