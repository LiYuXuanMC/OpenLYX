package al.nya.reflect.features.modules;

import al.nya.reflect.Reflect;
import al.nya.reflect.events.EventListener;
import al.nya.reflect.events.EventReceiver;
import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.Event;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.key.EnumKey;
import al.nya.reflect.utils.render.Translate;
import al.nya.reflect.value.Value;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.gui.ScaledResolution;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Module {
    @Getter
    private final String name;
    @Getter
    private final String translateName;
    @Getter
    private final ModuleType moduleType;
    private boolean enable = false;
    @Getter
    private final List<Value<?>> values = new ArrayList<>();
    public Minecraft mc = Minecraft.getMinecraft();
    private EnumKey binding = EnumKey.None;
    private Translate translate;
    public float lastRenderHeight = 0f;
    public Translate switchTranslate;
    @Getter
    @Setter
    private String describe = "";
    private boolean favorite = false;

    public Module(String name, String translateName, ModuleType moduleType) {
        this.translate = new Translate(new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() + 5, -5);
        this.translateName = translateName;
        this.switchTranslate = new Translate(5, 0);
        this.name = name;
        this.moduleType = moduleType;
        transformListener(this);
    }


    public Module(String name, ModuleType moduleType) {
        this(name, null, moduleType);
    }

    public void addValue(Value<?> v) {
        values.add(v);
    }

    public Translate getTranslate() {
        return this.translate;
    }

    public void addValues(Value<?>... vs) {
        for (Value v : vs) {
            addValue(v);
        }
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isEnable() {
        return enable;
    }
    public void setEnableNoNotification(boolean enable) {
        this.enable = enable;
        if (enable){
            onEnable();
        }else {
            this.translate = new Translate(new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() + 5, -5);
            onDisable();
        }
    }
    public void setEnable(boolean enable) {
        this.enable = enable;
        mc.getThePlayer().playSound("random.click",1,1);
        if (enable){
            onEnable();
            NotificationPublisher.queue("Enable",name+ " Has Been Enabled", 2000, NotificationType.SUCCESS);
        }else {
            this.translate = new Translate(new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() + 5, -5);
            onDisable();
            NotificationPublisher.queue("Disable",name+ " Has Been Disabled", 2000, NotificationType.ERROR);
        }
    }
    public void onEnable(){

    }
    public void onDisable(){

    }

    public EnumKey getBinding() {
        return binding;
    }

    public void setBinding(EnumKey binding) {
        this.binding = binding;
    }

    public String getSuffix() {
        return null;
    }
    private void transformListener(Module module){
        Method[] methods = this.getClass().getMethods();
        Method[] dMethods = this.getClass().getDeclaredMethods();
        List<Method> methodList = new ArrayList<Method>(Arrays.asList(methods));
        Arrays.asList(dMethods).forEach(M -> {
            for (Method method : methodList) {
                if (method.getName().equals(M.getName())){
                    return;
                }
            }
            methodList.add(M);
        });
        for (Method method : methodList) {
            method.setAccessible(true);
            for (Annotation declaredAnnotation : method.getDeclaredAnnotations()) {
                if (declaredAnnotation.annotationType() == EventTarget.class){
                    Parameter[] parameters = method.getParameters();
                    if (parameters.length == 1){
                        if (Reflect.Instance.eventBus.isRegisteredEvent(parameters[0].getType())){
                            Reflect.Instance.eventBus.registerListener(new EventListener(this,(Class<? extends Event>) parameters[0].getType(), new EventReceiver() {
                                @Override
                                public void receive(Event event) {
                                    try {
                                        method.invoke(module,event);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }));
                            break;
                        }
                    }
                }
            }
        }
    }
}
