package com.reflectmc.reflect.features.values;

public class ModeValueBind {
    private ModeValue mv;
    private Enum target;
    public ModeValueBind(ModeValue mv,Enum target){
        this.mv = mv;
        this.target = target;
    }
    public boolean isOnBind(){
        return mv.getValue() == target;
    }
}
