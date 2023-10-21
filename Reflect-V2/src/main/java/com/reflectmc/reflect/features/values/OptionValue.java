package com.reflectmc.reflect.features.values;

public class OptionValue extends Value<Boolean>{
    public OptionValue(String name, Boolean value) {
        super(name, value);
    }

    @Override
    public String save() {
        return getValue().toString();
    }

    @Override
    public void load(String v) {
        setValue(Boolean.parseBoolean(v));
    }
}
