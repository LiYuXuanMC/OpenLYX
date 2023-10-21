package cc.systemv.rave.utils.values;

public class OptionValue extends Value<Boolean>{
    public OptionValue(String name, Boolean defaultValue) {
        super(name, defaultValue);
    }

    @Override
    public String save() {
        return getValue().toString();
    }

    @Override
    public void load(String value) {
        setValue(Boolean.valueOf(value));
    }
}
