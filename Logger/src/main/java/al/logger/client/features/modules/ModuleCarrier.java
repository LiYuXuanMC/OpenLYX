package al.logger.client.features.modules;

import al.logger.client.Logger;
import al.logger.client.event.Event;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.listener.IEventConsumer;
import al.logger.client.utils.ReflectUtil;
import al.logger.client.utils.UnsafeUtils;
import al.logger.client.value.bases.Value;
import al.logger.client.value.impls.ModeValue;
import by.radioegor146.nativeobfuscator.NotNative;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleCarrier extends Module{
    private final Map<Enum<?>,Module> subModules = new HashMap<>();
    private final Map<Enum<?>,Map<Class<? extends Event>, List<IEventConsumer>>> subModuleListeners = new HashMap<>();
    private ModeValue usingSubModule;
    public ModuleCarrier(String name, String description, Category category) {
        super(name, description, category);
    }
    public ModuleCarrier(String name, Category category) {
        super(name, category);
    }

    protected void addSubModule(Module module){
        SubModuleKeyBase emptyEnum = UnsafeUtils.allocObject(SubModuleKeyBase.class);
        Field nameField = ReflectUtil.findField(Enum.class,"name");
        Field ordinalField = ReflectUtil.findField(Enum.class,"ordinal");
        UnsafeUtils.writeFinal(nameField,emptyEnum,module.getName());
        UnsafeUtils.writeFinal(ordinalField,emptyEnum,subModules.size());
        subModules.put(emptyEnum,module);
    }
    public void onEnable(){
        Module module = subModules.get(usingSubModule.getValue());
        if (!module.isEnable())
            toggleSubModule(module);
    }
    public void onDisable(){
        Module module = subModules.get(usingSubModule.getValue());
        if (module.isEnable())
            toggleSubModule(module);
    }
    private void toggleSubModule(Module module){
        if (module.isEnable()){
            module.setDisableNoNotification();
            module.onDisable();
        }else {
            module.setEnableNoNotification();
            module.onEnable();
        }
    }
    public void registerSubModuleEvent(){
        usingSubModule = new ModeValue("Mode",subModules.keySet().toArray(new Enum<?>[0])[0],subModules.keySet().toArray(new Enum<?>[0])){
            @Override
            public void onValueChange(Enum old,Enum newValue) {
                Module oldModule = subModules.get(old);
                Module newModule = subModules.get(newValue);
                if (isEnable()){
                    if (oldModule.isEnable())
                        toggleSubModule(oldModule);
                    if (!newModule.isEnable())
                        toggleSubModule(newModule);;
                }
            }
        };
        addValues(usingSubModule);
        subModules.forEach((key,module) -> {
            for (Value value : module.getValues()) {
                value.addCallBack(() -> usingSubModule.getValue() == key);
                addValues(value);
            }
        });
        for (Enum<?> anEnum : subModules.keySet()) {
            subModuleListeners.put(anEnum,new HashMap<>());
            for (Class<? extends Event> event : Logger.getInstance().getEventBus().getEvents()) {
                subModuleListeners.get(anEnum).put(event,new ArrayList<>());
            }
        }
        for (Map.Entry<Enum<?>, Module> entry : subModules.entrySet()) {
            Module module = entry.getValue();
            Class<? extends Module> moduleClass = module.getClass();
            for (Method declaredMethod : moduleClass.getDeclaredMethods()) {
                if (declaredMethod.getParameters().length != 1)
                    continue;
                Class<?> targetEvent = declaredMethod.getParameters()[0].getType();
                Listener annotation = ReflectUtil.getAnnotation(declaredMethod, Listener.class);
                if (annotation == null)
                    continue;
                IEventConsumer listener = new IEventConsumer(annotation.priority()) {
                    @Override
                    public void accept(Object o) {
                        if (module.isEnable()){
                            try {
                                declaredMethod.invoke(module,o);
                            } catch (Throwable e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                };
                subModuleListeners.get(entry.getKey()).get(targetEvent).add(listener);
            }
        }
        for (Class<? extends Event> event : Logger.getInstance().getEventBus().getEvents()) {
            IEventConsumer listener = new IEventConsumer(500){
                @Override
                public void accept(Object o) {
                    if (usingSubModule.getValue() != null) {
                        subModuleListeners.get(usingSubModule.getValue()).get(event).forEach(eventListener -> onEvent(eventListener, o));
                    }
                }
            };
            Logger.getInstance().getEventBus().registerListener(event,listener);
        }
    }
    @NotNative
    private void onEvent(IEventConsumer listener,Object event){
        try {
            listener.accept(event);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
