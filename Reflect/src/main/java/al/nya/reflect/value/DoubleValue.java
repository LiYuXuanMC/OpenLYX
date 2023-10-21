package al.nya.reflect.value;

import java.text.DecimalFormat;

public class DoubleValue extends NumberValue<Double>{
    private String inc;
    public DoubleValue(String name,double max,double min,double value,String inc) {
        super(name,max,min,value);
        this.inc = inc;
    }

    @Override
    public void setValue(Double d){
        DecimalFormat df = new DecimalFormat(inc);
        super.setValue(Double.parseDouble(df.format(d)));
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
