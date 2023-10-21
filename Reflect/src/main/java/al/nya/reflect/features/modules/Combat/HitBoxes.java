package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.*;
import al.nya.reflect.wrapper.wraps.wrapper.potion.Potion;

public class HitBoxes extends Module {
    DoubleValue expand = new DoubleValue("Expand", 2.0, 0.0, 1.0, "0.0");
    OptionValue extra = new OptionValue("Extra",false);
    DoubleValue extraV = new DoubleValue("ExtraExpand", 15.0, 0.0, 2.0, "0.0"){
        @Override
        public boolean show(){
            return extra.getValue();
        }
    };
    public OptionValue playerValue = new OptionValue("Player",true);
    public OptionValue animalValue = new OptionValue("Animal",false);
    public OptionValue mobValue = new OptionValue("Mob",false);
    public OptionValue invisibleValue = new OptionValue("Invisible",true);
    private OptionValue throughWallsValue = new OptionValue("ThroughWalls", true);
    public HitBoxes() {
        super("HitBoxes",ModuleType.Combat);
        addValues(expand,extra,extraV,playerValue,animalValue,mobValue,invisibleValue,throughWallsValue);
    }
    @EventTarget
    public void onUpdate(EventUpdate update){
        for (Entity entity : mc.getTheWorld().getLoadedEntityList()) {
            if ((!EntityPlayerSP.isEntityPlayerSP(entity) && entity.canBeCollidedWith())){
                if (EntityLivingBase.isEntityLivingBase(entity) && isValid(new EntityLivingBase(entity.getWrapObject()))){
                    entity.setWidth((float)(extra.getValue()?0.6 + expand.getValue()+extraV.getValue():0.6 + expand.getValue()));
                }
            }
        }
    }
    private boolean isValid(EntityLivingBase entity) {
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isPlayerSleeping()
                || thePlayer.isDead()
                || thePlayer.getHealth() <= 0
                || entity.isDead() || entity.getHealth() <= 0
                || EntityArmorStand.isEntityArmorStand(entity) || AntiBot.isEntityBot(entity)
                || EntityPlayerSP.isEntityPlayerSP(entity)) {
            return false;
        }
        if (!thePlayer.canEntityBeSeen(entity) && !throughWallsValue.getValue())return false;
        if (AntiBot.isEntityBot(entity)) return false;
        if (Teams.isTeam(entity)){
            return false;
        }
        if (EntityPlayer.isEntityPlayer(entity)) {
            EntityPlayer player = new EntityPlayer(entity.getWrapObject());

            if (!playerValue.getValue()) return false;

            if (player.isPlayerSleeping()) return false;

            if (player.isPotionActive(Potion.invisibility) && !invisibleValue.getValue()) return false;

        }

        if (EntityAnimal.isEntityAnimal(entity)) return animalValue.getValue();
        if (EntityVillager.isEntityVillager(entity)) return animalValue.getValue();
        if (EntityMob.isEntityMob(entity)) return mobValue.getValue();

        return true;
    }
}
