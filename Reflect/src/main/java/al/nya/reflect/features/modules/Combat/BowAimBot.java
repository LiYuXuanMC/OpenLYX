package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.features.modules.Visual.ESP;
import al.nya.reflect.utils.Rotation;
import al.nya.reflect.utils.RotationUtils;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.*;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemBow;
import al.nya.reflect.wrapper.wraps.wrapper.item.ItemStack;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BowAimBot extends Module {
    private final OptionValue silentValue = new OptionValue("Silent", true);
    private final OptionValue predictValue = new OptionValue("Predict", true);
    private final OptionValue throughWallsValue = new OptionValue("ThroughWalls", false);
    private final DoubleValue predictSizeValue = new DoubleValue("PredictSize", 5, 1, 2, "0.0");
    private final ModeValue priorityValue = new ModeValue("Priority", Priority.Direction, Priority.values());
    private final OptionValue markValue = new OptionValue("Mark", true);
    public OptionValue playerValue = new OptionValue("Player", true);
    public OptionValue animalValue = new OptionValue("Animal", false);
    public OptionValue mobValue = new OptionValue("Mob", false);
    public OptionValue invisibleValue = new OptionValue("Invisible", true);
    private Entity target = null;
    public static Rotation rotation = null;


    public BowAimBot() {
        super("BowAimBot", "箭矢追踪", ModuleType.Combat);
        addValues(silentValue, predictValue, throughWallsValue, predictSizeValue, priorityValue, markValue, playerValue, animalValue, mobValue, invisibleValue);
    }
    @Override
    public void onDisable() {
        target = null;
    }
    @EventTarget
    public void onUpdate(EventUpdate update) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        ItemStack item = thePlayer.getItemInUse();
        if (!item.isNull() && ItemBow.isItemBow(item.getItem())) {
            if (target == null){
                Entity entity = getTarget(throughWallsValue.getValue());
                if (entity == null){
                    rotation = null;
                    return;
                }
                target = entity;
            }
            rotation = RotationUtils.faceBow(target, silentValue.getValue(), predictValue.getValue(), predictSizeValue.getValue().floatValue());
            if (!silentValue.getValue())
            if (!Float.isNaN(rotation.getYaw()) && !Float.isNaN(rotation.getPitch())){
                rotation.toPlayer(thePlayer);
            }
        }else {
            rotation = null;
            target = null;
        }
    }
    public static float getYaw(){
        if (!ModuleManager.getModule(BowAimBot.class).isEnable())return Minecraft.getMinecraft().getThePlayer().getRotationYaw();
        if (rotation == null)return Minecraft.getMinecraft().getThePlayer().getRotationYaw();
        return rotation.getYaw();
    }
    public static float getPitch(){
        if (!ModuleManager.getModule(BowAimBot.class).isEnable())return Minecraft.getMinecraft().getThePlayer().getRotationPitch();
        if (rotation == null)return Minecraft.getMinecraft().getThePlayer().getRotationPitch();
        return rotation.getPitch();
    }
    @EventTarget
    public void onPre(EventPreUpdate preUpdate){
        if (silentValue.getValue() && rotation != null)
            if (!Float.isNaN(rotation.getYaw()) && !Float.isNaN(rotation.getPitch())){
                preUpdate.setPitch(rotation.getPitch());
                preUpdate.setYaw(rotation.getYaw());
            }
    }
    @EventTarget
    public void onRender3D(EventRender3D render3D) {
        if (target != null  && markValue.getValue())
            ESP.drawEntityBox(target,Color.RED,true);
    }
    private Entity getTarget(boolean throughWalls){
        ArrayList<Entity> targets = new ArrayList<Entity>();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        for (Entity entity : mc.getTheWorld().getLoadedEntityList()) {
            if (isValid(entity,throughWalls,thePlayer)) targets.add(entity);
        }
        return sortEntity(targets,thePlayer);
    }
    public Entity sortEntity(List<Entity> entities,EntityPlayerSP thePlayer){
        Entity best = null;
        float bestValue = 1000000F;
        for (Entity entity : entities) {
            if (priorityValue.getValue() == Priority.Distance) {
                float distance = thePlayer.getDistanceToEntity(entity);
                if (distance < bestValue){
                    bestValue = distance;
                    best = entity;
                }
            }
            if (priorityValue.getValue() == Priority.Direction) {
                float difference = (float) RotationUtils.getRotationDifference(entity);
                if (difference < bestValue){
                    bestValue = difference;
                    best = entity;
                }
            }
            if (priorityValue.getValue() == Priority.Health) {
                if (EntityLivingBase.isEntityLivingBase(entity)){
                    float health = new EntityLivingBase(entity.getWrapObject()).getHealth();
                    if (health < bestValue){
                        bestValue = health;
                        best = entity;
                    }
                }
            }
        }
        return best;
    }
    public boolean isValid(Entity entity,boolean throughWalls, EntityPlayerSP thePlayer){
        if (!EntityLivingBase.isEntityLivingBase(entity)){
            return false;
        }
        if (EntityPlayerSP.isEntityPlayerSP(entity)){
            return false;
        }
        if(EntityArmorStand.isEntityArmorStand(entity)) return false;
        if (!throughWalls){
            if (!thePlayer.canEntityBeSeen(entity)){
                return false;
            }
        }
        if (EntityPlayer.isEntityPlayer(entity)){
            if (!playerValue.getValue()) return false;
            if (Teams.isTeam(entity)) return false;
            if (AntiBot.isEntityBot(entity)) return false;
            if (new EntityPlayer(entity.getWrapObject()).isPotionActive(Potion.invisibility) && !invisibleValue.getValue())
                return false;
        }
        if (EntityAnimal.isEntityAnimal(entity)) return animalValue.getValue();
        if (EntityVillager.isEntityVillager(entity)) return animalValue.getValue();
        if (EntityMob.isEntityMob(entity)) return mobValue.getValue();
        return true;
    }

    public boolean hasTarget(){
        return target != null && mc.getThePlayer().canEntityBeSeen(target);
    }
    public enum Priority {
        Health,
        Distance,
        Direction
    }
}
