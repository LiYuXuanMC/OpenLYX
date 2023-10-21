package al.nya.reflect.features.modules.Ghost;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventUpdate;
import al.nya.reflect.features.modules.Combat.AntiBot;
import al.nya.reflect.features.modules.Combat.KillAura;
import al.nya.reflect.features.modules.Combat.Teams;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.EntitySelect;
import al.nya.reflect.utils.Rotation;
import al.nya.reflect.utils.RotationUtils;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.entity.*;

import java.util.Objects;

public class AimBot extends Module {
    private DoubleValue turnSpeed = new DoubleValue("TurnSpeed",100D,10D,50D,"0");
    private DoubleValue aimRange = new DoubleValue("AimRange",7D,1D,4D,"0.0");
    private ModeValue aimMode = new ModeValue("AimMode",AimMode.ClickAim,AimMode.values());
    private ModeValue priority = new ModeValue("Priority", Priority.Range, Priority.values());
    private OptionValue limitFOV = new OptionValue("LimitFOV",false);
    private DoubleValue fov = new DoubleValue("FOV",360D,0D,180D,"0.0"){
        @Override
        public boolean show(){
            return limitFOV.getValue();
        }
    };
    private EntitySelect select = new EntitySelect(true,false,false,true);
    public AimBot() {
        super("AimBot", ModuleType.Ghost);
        addValue(turnSpeed);
        addValue(aimRange);
        addValue(aimMode);
        addValues(select.getValues());
        addValues(priority,limitFOV,fov);
    }
    @EventTarget
    public void onUpdate(EventUpdate e){
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (!mc.getGameSettings().getKeyBindAttack().isKeyDown() && aimMode.getValue() != AimMode.AutoAim) return;
        if (thePlayer.isNull())return;
        if (mc.getPlayerController().getCurBlockDamageMP() != 0F) return;
        Entity targetEntity = getBestEntity(thePlayer);
        if (targetEntity == null) return;
        Rotation rotation = RotationUtils.limitAngleChange(
                new Rotation(thePlayer.getRotationYaw(), thePlayer.getRotationPitch()),
                RotationUtils.toRotation(RotationUtils.getCenter(targetEntity.getEntityBoundingBox()), true),
                (float) (turnSpeed.getValue().floatValue() + Math.random())
        );
        rotation.toPlayer(thePlayer);
    }
    public Entity getBestEntity(EntityPlayerSP thePlayer){
        Entity best = null;
        for (Entity entity : mc.getTheWorld().getLoadedEntityList()) {
            if (isValid(thePlayer,entity)){
                best = compare(thePlayer,best,entity);
            }
        }
        return best;
    }
    public Entity compare(EntityPlayerSP thePlayer,Entity entity1,Entity entity2){
        if (entity1 == null && entity2 == null) return null;
        if (entity1 == null) return entity2;
        if (entity2 == null) return entity1;
        if (priority.getValue() == Priority.Range){
            if (thePlayer.getDistanceToEntity(entity1) > thePlayer.getDistanceToEntity(entity2)) return entity2;
            else return entity1;
        }
        if (priority.getValue() == Priority.Angle){
            if (RotationUtils.getRotationDifference(entity1) > RotationUtils.getRotationDifference(entity2)) return entity2;
            else return entity1;
        }
        if (priority.getValue() == Priority.Fov){
            if (getDistanceBetweenAngles(thePlayer.getRotationYaw(), RotationUtils.getRotations(entity1)[0]) > getDistanceBetweenAngles(thePlayer.getRotationYaw(), RotationUtils.getRotations(entity2)[0])) return entity2;
            else return entity1;
        }
        if (priority.getValue() == Priority.Health){
            if (new EntityLivingBase(entity1.getWrapObject()).getHealth() > new EntityLivingBase(entity2.getWrapObject()).getHealth()) return entity2;
            else return entity1;
        }
        return null;
    }
    public boolean isValid(EntityPlayerSP thePlayer,Entity entity){
        if (!thePlayer.canEntityBeSeen(entity)) return false;
        if (thePlayer.getDistanceToEntity(entity) > aimRange.getValue()) return false;
        if (!EntityLivingBase.isEntityLivingBase(entity)) return false;
        if (thePlayer.isPlayerSleeping()
                || thePlayer.isDead()
                || thePlayer.getHealth() <= 0
                || entity.isDead()
                || EntityArmorStand.isEntityArmorStand(entity) || AntiBot.isEntityBot(entity)
                || EntityPlayerSP.isEntityPlayerSP(entity)) {
            return false;
        }
        if (limitFOV.getValue()){
            if (getDistanceBetweenAngles(thePlayer.getRotationYaw(), RotationUtils.getRotations(entity)[0]) > fov.getValue()){
                return false;
            }
        }
        if (!select.check(entity))return false;
        if (AntiBot.isEntityBot(entity)) return false;
        if (Teams.isTeam(entity)) return false;
        return true;
    }
    private float getDistanceBetweenAngles(float angle1, float angle2) {
        float agl = Math.abs(angle1 - angle2) % 360.0f;
        if (agl > 180.0f) {
            agl = 0.0f;
        }
        return agl-1;
    }
    public enum AimMode {
        ClickAim,
        AutoAim
    }
    public enum Priority{
        Range,
        Fov,
        Angle,
        Health
    }
}
