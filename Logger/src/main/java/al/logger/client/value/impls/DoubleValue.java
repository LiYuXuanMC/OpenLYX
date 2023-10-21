package al.logger.client.value.impls;

public class DoubleValue extends NumberValue<Double> {
    public double inc;

    public DoubleValue(String name, double max, double min, double value, double inc) {
        super(name, max, min, value);
        this.inc = inc;
    }

    @Override
    public String save() {
        return getValue().toString();
    }


    public double getINC() {
        return inc;
    }


    @Override
    public void load(String v) {
        setValue(Double.parseDouble(v));
    }
}
