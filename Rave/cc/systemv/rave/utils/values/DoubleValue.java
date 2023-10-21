package cc.systemv.rave.utils.values;

import lombok.Getter;

public class DoubleValue extends Value<Double> {
    @Getter
    private double inc;
    @Getter
    private double max;
    @Getter
    private double min;
    public DoubleValue(String name, double max, double min, double value, double inc) {
        super(name,value);
        this.inc = inc;
        this.max = max;
        this.min = min;
    }

    @Override
    public String save() {
        return getValue().toString();
    }

    @Override
    public void load(String v) {
        setValue(Double.parseDouble(v));
    }
}
