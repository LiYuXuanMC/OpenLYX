package al.nya.reflect.value;

import java.util.Arrays;
import java.util.List;

public class ModeValue extends Value<Enum>{
    private Enum[] values;
    public ModeValue(String name, Enum value,Enum[] values) {
        super(name, value);
        this.values = values;
    }

    @Override
    public String save() {
        return getValue().name();
    }

    public List<Enum> getValues() {
        return Arrays.asList(values);
    }

    @Override
    public void load(String v) {
        for (Enum value : values) {
            if (value.name().equals(v)){
                setValue(value);
            }
        }
    }
}
