package com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.event;

import com.reflectmc.reflect.wrapper.annotation.WrapConstructor;
import com.reflectmc.reflect.wrapper.annotation.WrapperClass;
import com.reflectmc.reflect.wrapper.mapper.Environment;
import com.reflectmc.reflect.wrapper.wrappers.WrapperBase;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text.IChatComponent;

import java.lang.reflect.Constructor;

@WrapperClass(deobfName = "net.minecraft.event.HoverEvent", targetEnvironment = {Environment.Forge189,Environment.Vanilla189})
@WrapperClass(deobfName = "net.minecraft.util.text.event.HoverEvent", targetEnvironment = {Environment.Forge1122,Environment.Vanilla1122})
public class HoverEvent extends WrapperBase {
    @WrapConstructor(targetEnvironment = {Environment.Forge189,Environment.Vanilla189}, signature = {HoverEventAction.class, IChatComponent.class})
    @WrapConstructor(targetEnvironment = {Environment.Forge1122, Environment.Vanilla1122}, signature = {HoverEventAction.class, IChatComponent.class})
    public static Constructor<?> HoverEvent_HoverEventAction_IChatComponent;

    public HoverEvent(Enum<?> e, IChatComponent c) {
        super(construct(HoverEvent_HoverEventAction_IChatComponent, e, c.getWrappedObject()));
    }

    public HoverEvent(Object obj) {
        super(obj);
    }
}
