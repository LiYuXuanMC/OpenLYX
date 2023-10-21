package al.nya.reflect.features.modules.Movement.speeds;

import al.nya.reflect.events.events.*;
import al.nya.reflect.value.Value;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;

import java.util.ArrayList;
import java.util.List;

public class SpeedModules {
    private Speeds type;
    private List<Value> values = new ArrayList<Value>();
    public Minecraft mc = Minecraft.getMinecraft();
    public SpeedModules(Speeds type){
        this.type = type;
    }
    public void addValue(Value v){values.add(v);}
    public void onMove(EventMove move){}
    public void onUpdate(EventUpdate update){}
    public void onTick(EventTick tick){}
    public void onDisable(){}
    public void onEnable(){}

    public void onPre(EventPreUpdate pre) {
    }

    public void onJump(EventJump jump) {
    }

    public Speeds getType() {
        return type;
    }
    public List<Value> getValues() {return values;}
}
