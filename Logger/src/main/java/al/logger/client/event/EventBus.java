package al.logger.client.event;

import al.logger.client.ExceptionHandler;
import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.*;
import al.logger.client.event.client.config.EventLoadConfig;
import al.logger.client.event.client.config.EventSaveConfig;
import al.logger.client.event.client.mac.EventMACMouseOver;
import al.logger.client.event.client.mac.EventMACProcessPacket;
import al.logger.client.event.client.mac.EventMACProcessUpdate;
import al.logger.client.event.client.player.*;
import al.logger.client.event.client.render.*;
import al.logger.client.event.client.world.EventBlockBB;
import al.logger.client.event.client.world.EventLoadWorld;
import al.logger.client.event.client.world.EventWorldEntityOperation;
import al.logger.client.event.listener.IEventConsumer;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.ModuleCarrier;
import al.logger.client.features.modules.ModuleManager;
import al.logger.client.features.modules.lock.Locks;
import al.logger.client.transform.TransformManager;
import al.logger.client.transform.transformers.*;
import al.logger.client.transform.transformers.fix.NACC02PacketUseEntityFix;
import al.logger.client.transform.transformers.fix.OrangemarshallBlockHitAnimationFix;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.wrapper.LoggerMC.network.server.S08.S08PacketPlayerPosLook;
import al.logger.client.wrapper.environment.Environment;
import al.logger.client.wrapper.environment.EnvironmentDetector;
import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventBus {
    private Map<Class<? extends Event>, List<IEventConsumer>> listeners = new HashMap<>();
    @Getter
    private List<Class<? extends Event>> events = new ArrayList<>();
    public EventBus() {
    }
    public void init(){
        registerEvents();
        registerListener(this);
    }
    private void registerEvents(){
        events.add(EventLoadConfig.class);
        events.add(EventSaveConfig.class);
        events.add(EventCloseChannel.class);

        //Public To Users
        events.add(EventAttack.class);
        events.add(EventJump.class);
        events.add(EventLivingUpdate.class);
        events.add(EventMove.class);
        events.add(EventPreUpdate.class);
        events.add(EventPostUpdate.class);
        events.add(EventPullback.class);
        events.add(EventPushBlock.class);
        events.add(EventRotationAnimation.class);
        events.add(EventStep.class);
        events.add(EventPlayerState.class);
        events.add(EventUpdate.class);
        events.add(EventBlockBB.class);
        events.add(EmotePreEvent.class);
        events.add(EmotePostEvent.class);
        events.add(EventAnimation.class);
        events.add(EventPostRenderLiving.class);
        events.add(EventPreRenderLiving.class);
        events.add(EventRender2D.class);
        events.add(EventRender3D.class);
        events.add(EventDrawBlockHighlight.class);
        events.add(EventText.class);
        events.add(EventCameraClip.class);
        events.add(EventRenderExpBar.class);
        events.add(EventRenderHorseJumpBar.class);
        events.add(EventRenderPlayerStats.class);
        events.add(EventRenderSelectedItem.class);
        events.add(EventPreRenderPlayer.class);
        events.add(EventCape.class);
        events.add(EventEntityAction.class);
        events.add(EventScreen.class);

        events.add(EventNameTag.class);
        events.add(EventKey.class);
        events.add(EventLoop.class);
        events.add(EventPacket.class);
        events.add(EventSendMessage.class);
        events.add(EventTick.class);
        events.add(EventWindowClick.class);
        events.add(EventMouse.class);

        events.add(EventWorldEntityOperation.class);
        events.add(EventLoadWorld.class);

        //MAC By-pai
        events.add(EventMACProcessPacket.class);
        events.add(EventMACProcessUpdate.class);
        events.add(EventMACMouseOver.class);
        for (Class<? extends Event> event : events) {
            listeners.put(event,new ArrayList<>());
        }
    }

    public void registerTransformers(){
        TransformManager transformManager = Logger.getInstance().getTransformManager();
        transformManager.addTransformer(new MinecraftTransformer());
        if (EnvironmentDetector.getModifier() == Environment.Forge){
            transformManager.addTransformer(new GuiIngameForgeTransformer());
        }
        if (EnvironmentDetector.hasMod(Environment.OrangemarshallBlockhitAnimation)){
            transformManager.addTransformer(new OrangemarshallBlockHitAnimationFix());
        }
        if (EnvironmentDetector.hasAntiCheat(Environment.NPlusAntiCheat)){
            transformManager.addTransformer(new NACC02PacketUseEntityFix());
        }
        transformManager.addTransformer(new GuiScreenTransformer());
        transformManager.addTransformer(new GuiIngameTransformer());
        transformManager.addTransformer(new EntityPlayerSPTransformer());
        transformManager.addTransformer(new NetworkManagerTransformer());
        transformManager.addTransformer(new NetworkPlayerInfoTransformer());
        transformManager.addTransformer(new ItemRendererTransformer());
        transformManager.addTransformer(new EntityRendererTransformer());
        transformManager.addTransformer(new PlayerControllerMPTransformer());
        transformManager.addTransformer(new RendererLivingEntityTransformer());
        transformManager.addTransformer(new EntityTransformer());
        transformManager.addTransformer(new EntityPlayerTransformer());
        transformManager.addTransformer(new EntityLivingBaseTransformer());
        transformManager.addTransformer(new WorldClientTransformer());
        transformManager.addTransformer(new FontRendererTransformer());
        transformManager.addTransformer(new ModelBipedTransformer());
        transformManager.addTransformer(new BlockTransformer());
        transformManager.addTransformer(new RenderPlayerTransformer());
        transformManager.addTransformer(new AbstractClientPlayerTransformer());
        registerTransformersBeta();
    }
    private void registerTransformersBeta(){

    }
    @Listener
    private void resetLocksPreUpdate(EventPreUpdate preUpdate){
        Logger.getInstance().getLockManager().getLock(Locks.KillAuraAttacking).setLocked(false);
        Logger.getInstance().getLockManager().getLock(Locks.Scaffolding).setLocked(false);
    }
    @Listener
    private void processPacket(EventPacket eventPacket){
        if (eventPacket.getType() == EventType.Receive){
            if (S08PacketPlayerPosLook.isS08PacketPlayerPosLook(eventPacket.getPacket())){
                processPullBack();
                return;
            }
        }
    }
    private void processPullBack(){
        callEvent(new EventPullback());
    }
    /*public void registerEvent(Class<? extends Event> event,EventListener listener){
        List<EventListener> listenerList = listeners.get(event);
        if (listenerList == null){
            throw new RuntimeException("Cannot register listener to unregistered event");
        }
        listenerList.add(listener);
    }

     */
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
            if (!events.contains(argClass)){
                System.out.println("[EventBus] Event "+argClass.getName()+" is not registered!");
                continue;
            }
            IEventConsumer<Event> consumer = new IEventConsumer<Event>(listener.priority()) {
                @Override
                public void accept(Event event) {
                    try {
                        declaredMethod.invoke(o,event);
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
            if (!events.contains(argClass)){
                System.out.println("[EventBus] Event "+argClass.getName()+" is not registered!");
                continue;
            }
            IEventConsumer<Event> consumer = new IEventConsumer<Event>(listener.priority()) {
                @Override
                public void accept(Event event) {
                    try {
                        declaredMethod.invoke(null,event);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            };
            registerListener((Class<? extends Event>) argClass,consumer);
        }
    }
    public void registerListener(Class<? extends Event> eventClass, IEventConsumer consumer){
        listeners.get(eventClass).add(consumer);
    }
    @SneakyThrows
    public void registerListeners(){
        ModuleManager moduleManager = Logger.getInstance().getModuleManager();
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
                if (!events.contains(argClass)){
                    System.out.println("[EventBus] Event "+argClass.getName()+" is not registered!");
                    continue;
                }
                IEventConsumer<Event> consumer = new IEventConsumer<Event>(listener.priority()) {
                    @Override
                    public void accept(Event event) {
                        try {
                            if (module.isEnable()){
                                declaredMethod.invoke(module,event);
                            }
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }
                };
                listeners.get(argClass).add(consumer);
                /*for (Value<?> value : module.getValues()) {
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
                */
            }
            if (module instanceof ModuleCarrier){
                ((ModuleCarrier) module).registerSubModuleEvent();
            }
        }
    }
    @ExportObfuscate(name = "callEvent")
    public void callEvent(Event event){
        List<IEventConsumer> listenersList = listeners.get(event.getClass());
        if (listenersList != null){
            for (IEventConsumer listener : listenersList) {
                try {
                    listener.accept(event);
                }catch (Throwable e){
                    ExceptionHandler.handle(e);
                    e.printStackTrace();
                }
            }
        }
    }
    public void sortEvent(){
        for (List<IEventConsumer> value : listeners.values()) {
            value.sort((o1, o2) -> o2.getPriority() - o1.getPriority());
        }
    }

}
