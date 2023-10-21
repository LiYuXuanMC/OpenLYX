package al.logger.client.utils;

import al.logger.client.Logger;
import al.logger.client.features.modules.impls.Combat.KillAura;
import al.logger.client.features.modules.impls.World.Team;
import al.logger.client.value.bases.Value;
import al.logger.client.value.impls.DoubleValue;
import al.logger.client.value.impls.OptionValue;
import al.logger.client.wrapper.LoggerMC.entity.*;
import al.logger.client.wrapper.LoggerMC.potion.Potion;
import com.sun.org.omg.CORBA.portable.ValueHelper;
import lombok.Getter;

public class EntityValid {
    @Getter
    private OptionValue playerValue = new OptionValue("Player",true);
    @Getter
    private OptionValue animalValue = new OptionValue("Animal",false);
    @Getter
    private OptionValue mobValue = new OptionValue("Mob",false);
    @Getter
    private OptionValue invisibleValue = new OptionValue("Invisible",true);
    @Getter
    private OptionValue wallValue = new OptionValue("ThroughWall",true);
   // @Getter
   // private DoubleValue throughWallrange = new DoubleValue("ThroughWall Range",3,0.1,10,0.1);
    public EntityValid(boolean player, boolean animal, boolean mob, boolean invisible,boolean throughWall){
        playerValue.setValue(player);
        animalValue.setValue(animal);
        mobValue.setValue(mob);
        invisibleValue.setValue(invisible);
        wallValue.setValue(throughWall);
    }
    public Value[] getValues(){
        return new Value[]{playerValue,animalValue,mobValue,invisibleValue,wallValue};
    }
    public boolean check(Entity entity,EntityPlayerSP thePlayer){
        if (entity.isNull()) return false;
        if (!EntityLivingBase.isEntityLivingBase(entity)) return false;
        if (EntityArmorStand.isEntityArmorStand(entity)) return false;
        EntityLivingBase livingBase = new EntityLivingBase(entity.getWrappedObject());
        if (!thePlayer.canEntityBeSeen(entity)) {
            if (!wallValue.getValue())
                return false;
        }

        if (!wallValue.getValue()){
            if (!thePlayer.canEntityBeSeen(entity)){
                return false;
            }
        }

        if (thePlayer.isPlayerSleeping()
                || thePlayer.isDead()
                || thePlayer.getHealth() <= 0
                || entity.isDead() || livingBase.getHealth() <= 0
                || EntityPlayerSP.isEntityPlayerSP(entity)
                || Team.isTeam(entity)) {
            return false;
        }
        if (EntityPlayer.isEntityPlayer(entity)) {
            if (!playerValue.getValue())
                return false;
            EntityPlayer player = new EntityPlayer(entity.getWrappedObject());
            if (player.isPlayerSleeping()) return false;
            if (Logger.getInstance().getFriendManager().isFriend(player.getName()))
                return false;
        }

        if (new EntityLivingBase(entity.getWrappedObject()).isPotionActive(Potion.invisibility) && !invisibleValue.getValue())
            return false;
        if (EntityMob.isEntityMob(entity)) return mobValue.getValue();
        if (IAnimals.isIAnimals(entity)) return animalValue.getValue();
        if (EntityVillager.isEntityVillager(entity)) return animalValue.getValue();
        return true;
    }
}
