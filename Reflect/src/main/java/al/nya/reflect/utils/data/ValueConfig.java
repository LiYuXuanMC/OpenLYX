package al.nya.reflect.utils.data;

import al.nya.reflect.value.Value;

public class ValueConfig {
    public String name;
    public String value;
    public ValueConfig(Value v){
        this.name = v.getName();
        this.value = v.save();
    }
}
