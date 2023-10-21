package al.logger.client.features.modules;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventPacket;
import al.logger.client.event.client.EventTick;
import al.logger.client.event.client.player.EventJump;
import al.logger.client.event.client.player.EventMove;
import al.logger.client.event.client.player.EventPostUpdate;
import al.logger.client.event.client.player.EventPreUpdate;
import al.logger.client.ui.bases.components.Notification;
import al.logger.client.ui.builders.NotificationBuilder;
import al.logger.client.utils.animation.logger.Smoother;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;
import al.logger.client.value.bases.Value;
import al.logger.client.wrapper.LoggerMC.Minecraft;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Module {
    @Getter
    private ArrayList<Value> values = new ArrayList<>();
    @Getter
    private String name;
    @Getter
    private Smoother animation = new Smoother(0f, 60);
    @Getter
    private Smoother yAnimation = new Smoother(0f, 60);
    @Getter
    private String displayName;
    @Getter
    private Category category;
    @Getter
    @Setter
    private int keyCode = 0;
    private boolean enable = false;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Hazard hazard;
    @Getter
    @Setter
    private Boolean openness = false;

    protected final Minecraft mc = Minecraft.getMinecraft();
    protected final Logger logger = Logger.getInstance();
    @Getter
    @Setter
    private boolean hide = false;
    public Module(String name, Category category) {
        this(name, "No description", category);
    }

    public Module(String name, String description, Category category) {
        this.name = name;
        this.category = category;
        this.displayName = name;
        this.description = description;
        this.hazard = Hazard.NONE;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void addValues(Value... vls) {
        for (Value vl : vls) {
            Logger.getInstance().valueManager.createValue(this, vl);
        }
        values.addAll(Arrays.asList(vls));
    }

    public void setEnable() {
        setEnableNoNotification();
    }

    public void setDisable() {
        setDisableNoNotification();
    }

    public void toggle() {
        if (this.enable) {
            setDisable();
            Logger.getInstance().notificationManager.addNotification(new NotificationBuilder()
                    .setMessage(this.getDisplayName() + " is Disabled")
                    .setType(Notification.NotificationType.Error)
                    .createNotification());
        } else {
            setEnable();
            Logger.getInstance().notificationManager.addNotification(new NotificationBuilder()
                    .setMessage(this.getDisplayName() + " is Enable")
                    .setType(Notification.NotificationType.Success)
                    .createNotification());
        }
    }

    public void setEnabled(boolean isEnabled) {
        if (!isEnabled) {
            setDisable();
        } else {
            setEnable();
        }
    }
    public void setHide(boolean hide){
        this.hide = hide;
    }
    public boolean isHide(){
        return hide;
    }

    public boolean getDisplay(){
        return !hide;
    }

    public void setEnableNoNotification() {
        this.enable = true;
        System.out.println(this.getDisplayName() + " is Enabled");
        this.onEnable();
    }

    public void setDisableNoNotification() {
        this.enable = false;
        System.out.println(this.getDisplayName() + " is Disabled");
        this.onDisable();
    }

    @Listener
    public void onTick(EventTick e){

    }
    @Listener
    public void onPre(EventPreUpdate e){

    }
    @Listener
    public void onPost(EventPostUpdate e){

    }
    @Listener
    public void onJump(EventJump e){

    }

    @Listener
    public void onMove(EventMove e){

    }

    @Listener
    public void onPacket(EventPacket e){

    }
    @ExportObfuscate(name = "isEnable")
    public boolean isEnable() {
        return enable;
    }
}
