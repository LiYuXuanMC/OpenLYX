package com.reflectmc.reflect.features.values;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MultiSelectValue extends Value<List<Enum<?>>>{
    @Getter
    private Enum<?>[] values;
    public MultiSelectValue(String name,List<Enum<?>> selected, Enum<?>[] values) {
        super(name, selected);
        this.values = values;
    }

    @Override
    public String save() {
        StringBuilder sb = new StringBuilder();
        for (Enum<?> anEnum : getValue()) {
            sb.append(anEnum.name()).append("|");
        }
        return sb.toString();
    }

    @Override
    public void load(String v) {
        String[] strings = v.split("\\|");
        List<Enum<?>> selected = new ArrayList<>();
        for (String string : strings) {
            if (!string.isEmpty()){
                for (Enum<?> value : values) {
                    if (value.name().equals(string))
                        selected.add(value);
                }
            }
        }
        this.setValue(selected);
    }

    public void toggleValue(Enum<?> e){
        List<Enum<?>> enums = getValue();
        if (isValueToggle(e)){
            enums.remove(e);
        }else {
            enums.add(e);
        }
        setValue(enums);
    }

    public boolean isValueToggle(Enum<?> e){
        for (Enum<?> anEnum : getValue()) {
            if (anEnum == e) return true;
        }
        return false;
    }

}
