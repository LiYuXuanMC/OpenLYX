package com.reflectmc.reflect.features.values;

import com.reflectmc.reflect.utils.ClientUtil;
import com.reflectmc.reflect.utils.render.Translate;
import lombok.Getter;

import java.text.DecimalFormat;
import java.util.Objects;

public class MinMaxValue extends Value<Double[]>{
    @Getter
    private Double min;
    @Getter
    private Double max;
    @Getter
    private Translate minTranslate = new Translate(0,0);
    @Getter
    private Translate maxTranslate = new Translate(0,0);
    private String inc;
    public MinMaxValue(String name,Double min,Double max,Double minimum,Double maximum,String inc) {
        super(name, new Double[]{min,max});
        this.min = minimum;
        this.max = maximum;
        this.inc = inc;
    }

    @Override
    public String save() {
        return getValue()[0].toString()+"|"+getValue()[1].toString();
    }
    @Override
    public void setValue(Double[] d){
        DecimalFormat df = new DecimalFormat(inc);
        Double[] v = new Double[]{Double.parseDouble(df.format(d[0])), Double.parseDouble(df.format(d[1]))};
        if (!Objects.equals(v[0], getValue()[0])){
            //Min Move
            if (v[0] > v[1]){
                //Min > Max
                v = new Double[]{v[0],v[0]};
            }
        }else if (!Objects.equals(v[1], getValue()[1])){
            //Max Move
            if (v[1] < v[0]){
                //Max < Min
                v = new Double[]{v[1],v[1]};
            }
        }
        super.setValue(v);
    }

    @Override
    public void load(String v) {
        String[] strings = v.split("\\|");
        setValue(new Double[]{Double.parseDouble(strings[0]),Double.parseDouble(strings[1])});
    }
}
