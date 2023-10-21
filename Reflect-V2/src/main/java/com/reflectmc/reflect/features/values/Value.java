package com.reflectmc.reflect.features.values;

import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.utils.render.Translate;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class Value<V> {
    private String name;
    private V value;
    public Translate translate;
    @Getter
    @Setter
    private String displayName;
    private List<Object> binds = new ArrayList<>();
    public Value(String name,V value,Object... bind){
        this.name = name;
        this.value = value;
        this.displayName = name;
        for (Object o : bind) {
            binds.add(o);
        }
    }

    public void addBind(Object o){
        binds.add(o);
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        onValueChange(value,value);
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public abstract String save();
    public abstract void load(String v);
    public boolean show(){
        return true;
    }
    public boolean canShow(){
        return show() && checkBinds();
    }
    private boolean checkBinds(){
        for (Object bind : binds) {
            if (bind instanceof Module){
                return ((Module) bind).isEnable();
            }
            if (bind instanceof OptionValue){
                return ((OptionValue) bind).getValue();
            }
            if (bind instanceof ModeValueBind){
                return ((ModeValueBind) bind).isOnBind();
            }
        }
        return true;
    }
    public void onValueChange(V before,V after){

    }
}
