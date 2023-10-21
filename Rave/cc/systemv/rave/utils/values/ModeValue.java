package cc.systemv.rave.utils.values;

import cc.systemv.rave.feature.module.SubModule;

import java.util.ArrayList;
import java.util.List;

public class ModeValue extends Value<SubModule> {
    private List<SubModule> subModules = new ArrayList<>();
    public ModeValue(String name) {
        super(name,null);
    }
    public ModeValue addMode(SubModule subModule){
        subModules.add(subModule);
        return this;
    }
    public ModeValue selectMode(){
        setValue(subModules.get(0));
        return this;
    }

    @Override
    public String save() {
        return getValue().getKey().name();
    }

    public List<SubModule> getValues() {
        return subModules;
    }

    @Override
    public void load(String v) {
        for (SubModule value : subModules) {
            if (value.getKey().name().equals(v)){
                setValue(value);
            }
        }
    }
}
