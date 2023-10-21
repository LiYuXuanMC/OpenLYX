package com.reflectmc.reflect.features.modules;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.event.Listener;
import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.client.EventConfigLoad;
import com.reflectmc.reflect.event.events.client.EventConfigSave;
import com.reflectmc.reflect.features.values.Value;
import com.reflectmc.reflect.key.EnumKey;
import com.reflectmc.reflect.notification.NotificationManager;
import com.reflectmc.reflect.obfuscate.ExportObfuscate;
import com.reflectmc.reflect.utils.render.Translate;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.gui.ScaledResolution;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Annotation;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Module {
    @Getter
    private final String name;
    @Getter
    private final Category category;
    private boolean enable;
    @Getter
    private Translate translate;
    @Getter
    private final List<Value> values;
    public final Minecraft mc;
    @Getter
    @Setter
    private EnumKey bind = EnumKey.None;
    public Translate switchTranslate;
    @Getter
    @Setter
    public String describe = "";
    @Getter
    @Setter
    public boolean favorite = false;
    @Getter
    @Setter
    private String displayName;
    @Getter
    private List<SubModule> subModules = new ArrayList<>();
    public Module(String name,Category category){
        this.name = name;
        this.category = category;
        this.enable = false;
        this.values = new ArrayList<>();
        this.mc = Minecraft.getMinecraft();
        this.translate = new Translate(new ScaledResolution(mc).getScaledWidth() + 5, -5);
        this.switchTranslate = new Translate(5, 0);
        this.displayName = name;
    }
    public void addValue(Value v){
        values.add(v);
    }
    public void addValues(Value... vs){
        for (Value v : vs) {
            addValue(v);
        }
    }
    public void onEnable(){
        //Override
    }
    public void onDisable(){
        //Override
    }
    public void addSubModule(SubModule subModule){
        subModules.add(subModule);
    }
    @ExportObfuscate(name = "isEnable")
    public boolean isEnable(){
        return enable;
    }
    public String getSuffix(){
        return null;
    }
    public void setEnable(boolean enable){
        setEnableNoNotification(enable);
        NotificationManager.publishModule(this);
    }
    public void setEnableNoNotification(boolean enable){
        this.enable = enable;
        if (enable) onEnable(); else onDisable();
        this.translate = new Translate(new ScaledResolution(mc).getScaledWidth() + 5, -5);
    }
    public void registerEvent(){
        for (Method declaredMethod : this.getClass().getDeclaredMethods()) {
            for (Annotation declaredAnnotation : declaredMethod.getDeclaredAnnotations()) {
                if (declaredAnnotation instanceof EventTarget){
                    declaredMethod.setAccessible(true);
                    MethodHandles.Lookup lookup = MethodHandles.lookup();
                    MethodHandle handle = null;
                    try {
                        handle = lookup.unreflect(declaredMethod);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    MethodHandle finalHandle = handle; //Faster call
                    Consumer<Event> consumer = event -> {
                        if (this.isEnable())
                        try {
                            finalHandle.invoke(this,event);
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    };
                    Class<?> paramterEvent = declaredMethod.getParameters()[0].getType();
                    if (paramterEvent == EventConfigSave.class || paramterEvent == EventConfigLoad.class){
                        consumer = event -> {
                            try {
                                finalHandle.invoke(this,event);
                            } catch (Throwable e) {
                                e.printStackTrace();
                            }
                        };
                    }
                    Listener listener = new Listener(((EventTarget) declaredAnnotation).priority(),consumer);
                    Reflect.getINSTANCE().getEventBus().registerListener((Class<? extends Event>) paramterEvent,listener);
                }
            }
        }
    }
}
