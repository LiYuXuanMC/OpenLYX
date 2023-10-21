package cc.systemv.rave.feature.module;

import cc.systemv.rave.key.EnumKey;
import cc.systemv.rave.utils.InstanceAccess;
import cc.systemv.rave.utils.values.ModeValue;
import cc.systemv.rave.utils.values.Value;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Module extends InstanceAccess {
    @Getter
    private final String name;
    @Getter
    private final Category category;
    private boolean enabled;
    @Getter
    @Setter
    private EnumKey bind = EnumKey.None;
    @Getter
    private final List<Value<?>> values = new ArrayList<>();
    public Module(String name,Category category){
        this.name = name;
        this.category = category;
    }
    public String getSuffix(){
        return null;
    }
    protected void onEnable() {
    }

    protected void onDisable() {
    }
    protected void addValues(Value<?>... vls) {
        for (Value<?> vl : vls) {
            values.add(vl);
            if (vl instanceof ModeValue){
                for (SubModule value : ((ModeValue) vl).getValues()) {
                    for (Value<?> valueValue : value.getValues()) {
                        valueValue.addCallback(() -> vl.getValue() == value);
                        values.add(valueValue);
                    }
                }
            }
        }
    }
    public void setEnabled(boolean isEnabled) {
        if (!isEnabled) {
            setDisable();
        } else {
            setEnable();
        }
    }
    public void setEnable() {
        setEnableNoNotification();
    }

    public void setDisable() {
        setDisableNoNotification();
    }
    public void toggle() {
        if (this.enabled) {
            setDisable();
            //SystemClient.getInstance().getNotificationManager().addToQueue(new ModuleNotification(this, 2000));
        } else {
            setEnable();
            //SystemClient.getInstance().getNotificationManager().addToQueue(new ModuleNotification(this, 2000));
        }
    }

    public void setEnableNoNotification() {
        this.enabled = true;
        this.onEnable();
    }

    public void setDisableNoNotification() {
        this.enabled = false;
        this.onDisable();
    }

    public boolean isEnabled() {
        return enabled;
    }
}
