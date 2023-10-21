package com.reflectmc.reflect.event;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.event.events.EventRender;
import com.reflectmc.reflect.event.events.client.EventConfigLoad;
import com.reflectmc.reflect.event.events.client.EventConfigSave;
import com.reflectmc.reflect.event.events.game.*;
import com.reflectmc.reflect.event.events.player.*;
import com.reflectmc.reflect.event.events.render.EventRender2D;
import com.reflectmc.reflect.event.events.render.EventRender3D;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.obfuscate.ExportObfuscate;
import com.reflectmc.reflect.utils.render.AnimationUtils;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import org.lwjgl.Sys;

import java.util.*;

public class EventBus {
    private final List<Class<? extends Event>> events = new ArrayList<>();
    private final Map<Class<? extends Event>,List<Listener>> listeners = new HashMap<>();
    private long lastFrame;
    public EventBus(){

    }
    public void init(){
        events.add(EventRender.class);
        events.add(EventRender2D.class);
        events.add(EventRender3D.class);
        events.add(EventLoop.class);
        events.add(EventTick.class);
        events.add(EventKey.class);
        events.add(EventPostUpdate.class);
        events.add(EventPreUpdate.class);
        events.add(EventLivingUpdate.class);
        events.add(EventPacket.class);
        events.add(EventRotationAnimation.class);
        events.add(EventMove.class);
        events.add(EventAttack.class);
        events.add(EventSendMessage.class);
        events.add(EventConfigSave.class);
        events.add(EventConfigLoad.class);

        for (Class<? extends Event> event : events) {
            listeners.put(event,new ArrayList<>());
        }
        registerListener(EventKey.class,new Listener(Priority.Highest,e -> keyEvent((EventKey) e)));
        registerListener(EventRender2D.class,new Listener(Priority.Highest,e -> render2DEvent((EventRender2D) e)));
        registerListener(EventSendMessage.class,new Listener(Priority.Highest,e -> Reflect.getINSTANCE().getCommandManager().onMessage((EventSendMessage) e)));
    }
    public void registerListener(Class<? extends Event> event,Listener listener){
        List<Listener> listenerList = listeners.get(event);
        listenerList.add(listener);
    }
    public void sortEvent(){
        for (List<Listener> value : listeners.values()) {
            value.sort((o1, o2) -> o2.getPriority().getOrder() - o1.getPriority().getOrder());
        }
    }
    public void keyEvent(EventKey key){
        for (Module module : Reflect.getINSTANCE().getModuleManager().getModules()) {
            if (module.getBind().getKeyCode() == key.getKey()){
                module.setEnable(!module.isEnable());
            }
        }
    }
    public void render2DEvent(EventRender2D render2D){
        final long currentTime = (Sys.getTime() * 1000) / Sys.getTimerResolution();
        final double deltaTime = (int) Math.max((currentTime - lastFrame),1);
        lastFrame = currentTime;
        AnimationUtils.delta = deltaTime;
    }
    @ExportObfuscate(name = "callEvent")
    public void callEvent(Event event){
        if (!Reflect.LOAD_SUCCEED){
            return;
        }
        if (event instanceof EventKey){
            if (!Minecraft.getMinecraft().getCurrentScreen().isNull())
                return;
        }
        listeners.forEach((E,L) -> {
            if (E.isInstance(event)){
                for (Listener eventConsumer : L) {
                    eventConsumer.getConsumer().accept(event);
                }
            }
        });
    }
}
