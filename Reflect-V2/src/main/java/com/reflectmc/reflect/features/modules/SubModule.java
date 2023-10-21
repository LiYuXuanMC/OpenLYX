package com.reflectmc.reflect.features.modules;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.event.Listener;
import com.reflectmc.reflect.event.annotation.EventTarget;

import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.util.function.Consumer;

public class SubModule extends Module{
    public SubModule(Enum<?> type) {
        super(type.name(),null);
    }
    @Override
    public void setEnable(boolean enable){
        setEnableNoNotification(enable);
    }
}
