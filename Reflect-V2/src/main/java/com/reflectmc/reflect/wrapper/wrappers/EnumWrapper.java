package com.reflectmc.reflect.wrapper.wrappers;

public class EnumWrapper extends WrapperBase{
    public EnumWrapper(Object wrap) {
        super(wrap);
    }
    public boolean equals(EnumWrapper wrapper){
        return wrapper.getWrappedObject() == getWrappedObject();
    }
}
