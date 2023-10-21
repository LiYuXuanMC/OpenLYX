package cc.systemv.rave.event;

import cc.systemv.rave.Rave;
import cc.systemv.rave.event.annotation.Listener;
import cc.systemv.rave.feature.module.Module;
import cc.systemv.rave.feature.module.ModuleManager;
import cc.systemv.rave.feature.module.SubModule;
import cc.systemv.rave.utils.IManager;
import cc.systemv.rave.utils.values.ModeValue;
import cc.systemv.rave.utils.values.Value;
import cc.systemv.rave.event.events.*;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus extends IManager {
    private final List<Class<? extends Event>> registeredEvents = new ArrayList<>();
    private final Map<Class<? extends Event>,List<IEventConsumer<Event>>> listenerMap = new HashMap<>();
    private final static Logger logger = LogManager.getLogger("EventBus");
    private final MethodHandles.Lookup lookup = MethodHandles.lookup();
    public EventBus(){

    }
    public void init(){
        registeredEvents.add(EventKey.class);
        registeredEvents.add(EventRender2D.class);
        registeredEvents.add(EventRender3D.class);
        registeredEvents.add(EventPreUpdate.class);
        registeredEvents.add(EventPreMotion.class);
        registeredEvents.add(EventPostMotion.class);
        registeredEvents.add(EventPacket.class);
        registeredEvents.add(EventPullBack.class);
        registeredEvents.add(EventMove.class);
        registeredEvents.add(EventUpdate.class);
        registeredEvents.add(EventTick.class);
        registeredEvents.add(EventLoop.class);
        registeredEvents.add(EventLivingUpdate.class);
        registeredEvents.add(EventRotationAnimation.class);
        registeredEvents.add(EventNoSlow.class);
        registeredEvents.add(EventMoveInput.class);

        for (Class<? extends Event> registeredEvent : registeredEvents) {
            listenerMap.put(registeredEvent,new ArrayList<>());
        }
    }
    public void callEvent(Event event){
        try {
            listenerMap.get(event.getClass()).forEach(eventConsumer -> eventConsumer.accept(event));
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }
    public void registerListener(Class<? extends Event> eventClass, IEventConsumer consumer){
        listenerMap.get(eventClass).add(consumer);
    }
    public void sortEvent(){
        //get listener count
        Map<Class<? extends Event>,Integer> listenerCount = new HashMap<>();
        for (Map.Entry<Class<? extends Event>, List<IEventConsumer<Event>>> entry : listenerMap.entrySet()) {
            listenerCount.put(entry.getKey(),entry.getValue().size());
        }
        int count = 0;
        for (Map.Entry<Class<? extends Event>, Integer> entry : listenerCount.entrySet()) {
            count += entry.getValue();
        }
        logger.info("Listeners: " + count);
        for (List<IEventConsumer<Event>> value : listenerMap.values()) {
            value.sort((o1, o2) -> o2.getPriority() - o1.getPriority());
        }
    }
    @SneakyThrows
    public void registerListener(Object o){
        Class<?> clazz = o.getClass();
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if (declaredMethod.getDeclaredAnnotation(Listener.class) == null){
                continue;
            }
            Listener listener = declaredMethod.getDeclaredAnnotation(Listener.class);
            if (declaredMethod.getParameters().length != 1){
                continue;
            }
            if (Modifier.isStatic(declaredMethod.getModifiers())){
                continue;
            }
            if (!declaredMethod.isAccessible()){
                declaredMethod.setAccessible(true);
            }
            Class<?> argClass = declaredMethod.getParameters()[0].getType();
            if (!Event.class.isAssignableFrom(argClass)){
                continue;
            }
            if (!registeredEvents.contains(argClass)){
                logger.error("[EventBus] Event "+argClass.getName()+" is not registered!");
                continue;
            }
            MethodHandle methodHandle = lookup.unreflect(declaredMethod);
            IEventConsumer<Event> consumer = new IEventConsumer<Event>(listener.priority()) {
                @Override
                public void accept(Event event) {
                    try {
                        methodHandle.invoke(o,event);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            };
            registerListener((Class<? extends Event>) argClass,consumer);
        }
    }
    @SneakyThrows
    public void registerListener(Class<?> clazz){
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if (declaredMethod.getDeclaredAnnotation(Listener.class) == null){
                continue;
            }
            Listener listener = declaredMethod.getDeclaredAnnotation(Listener.class);
            if (declaredMethod.getParameters().length != 1){
                continue;
            }
            if (!Modifier.isStatic(declaredMethod.getModifiers())){
                continue;
            }
            if (!declaredMethod.isAccessible()){
                declaredMethod.setAccessible(true);
            }
            Class<?> argClass = declaredMethod.getParameters()[0].getType();
            if (!Event.class.isAssignableFrom(argClass)){
                continue;
            }
            if (!registeredEvents.contains(argClass)){
                logger.error("[EventBus] Event "+argClass.getName()+" is not registered!");
                continue;
            }
            MethodHandle methodHandle = lookup.unreflect(declaredMethod);
            IEventConsumer<Event> consumer = new IEventConsumer<Event>(listener.priority()) {
                @Override
                public void accept(Event event) {
                    try {
                        methodHandle.invoke(event);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            };
            registerListener((Class<? extends Event>) argClass,consumer);
        }
    }

    @SneakyThrows
    public void registerListeners(){
        ModuleManager moduleManager = Rave.getInstance().getModuleManager();
        for (Module module : moduleManager.getModules()) {
            for (Method declaredMethod : module.getClass().getDeclaredMethods()) {
                if (declaredMethod.getDeclaredAnnotation(Listener.class) == null){
                    continue;
                }
                Listener listener = declaredMethod.getDeclaredAnnotation(Listener.class);
                if (declaredMethod.getParameters().length != 1){
                    continue;
                }
                if (Modifier.isStatic(declaredMethod.getModifiers())){
                    continue;
                }
                if (!declaredMethod.isAccessible()){
                    declaredMethod.setAccessible(true);
                }
                Class<?> argClass = declaredMethod.getParameters()[0].getType();
                if (!Event.class.isAssignableFrom(argClass)){
                    continue;
                }
                if (!registeredEvents.contains(argClass)){
                    logger.error("[EventBus] Event "+argClass.getName()+" is not registered!");
                    continue;
                }
                MethodHandle methodHandle = lookup.unreflect(declaredMethod);
                IEventConsumer<Event> consumer = new IEventConsumer<Event>(listener.priority()) {
                    @Override
                    public void accept(Event event) {
                        try {
                            if (module.isEnabled()){
                                methodHandle.invoke(module,event);
                            }
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                };
                listenerMap.get(argClass).add(consumer);
                for (Value<?> value : module.getValues()) {
                    if (value instanceof ModeValue){
                        ModeValue modeValue = (ModeValue) value;
                        for (SubModule subModule : modeValue.getValues()) {
                            for (Method declaredMethod1 : subModule.getClass().getDeclaredMethods()) {
                                if (declaredMethod1.getDeclaredAnnotation(Listener.class) == null){
                                    continue;
                                }
                                Listener listener1 = declaredMethod1.getDeclaredAnnotation(Listener.class);
                                if (declaredMethod1.getParameters().length != 1){
                                    continue;
                                }
                                if (Modifier.isStatic(declaredMethod1.getModifiers())){
                                    continue;
                                }
                                if (!declaredMethod1.isAccessible()){
                                    declaredMethod1.setAccessible(true);
                                }
                                Class<?> argClass1 = declaredMethod1.getParameters()[0].getType();
                                if (!Event.class.isAssignableFrom(argClass1)){
                                    continue;
                                }
                                if (!registeredEvents.contains(argClass1)){
                                    logger.error("[EventBus] Event "+argClass1.getName()+" is not registered!");
                                    continue;
                                }
                                MethodHandle methodHandle1 = lookup.unreflect(declaredMethod1);
                                IEventConsumer<Event> consumerSubModule = new IEventConsumer<Event>(listener1.priority()) {
                                    @Override
                                    public void accept(Event event) {
                                        try {
                                            if (module.isEnabled()){
                                                if (modeValue.getValue().equals(subModule)){
                                                    methodHandle1.invoke(subModule,event);
                                                }
                                            }
                                        } catch (Throwable e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                listenerMap.get(argClass1).add(consumerSubModule);
                            }
                        }
                    }
                }
            }
        }
    }
}
