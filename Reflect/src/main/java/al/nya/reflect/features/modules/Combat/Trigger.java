package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventRender3D;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.EntitySelect;
import al.nya.reflect.value.DoubleValue;
import al.nya.reflect.wrapper.wraps.wrapper.KeyBinding;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityArmorStand;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityLivingBase;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import lombok.val;

public class Trigger extends Module {
    private EntitySelect select = new EntitySelect(true,false,false,true);
    private DoubleValue maxCPS = new DoubleValue("MaxCPS", 20, 1, 8,"0") {
        public void onValueChange() {
            double i = minCPS.getValue();
            if (i > getValue()) setValue(i);
            delay = randomClickDelay(minCPS.getValue().intValue(),getValue().intValue());
        }
    };
    private DoubleValue minCPS = new DoubleValue("MinCPS", 20,1,5,"0") {
        public void onValueChange() {
            val i = maxCPS.getValue();
            if (i < getValue()) setValue(i);
            delay = randomClickDelay(this.getValue().intValue(), maxCPS.getValue().intValue());
        }
    };
    private long delay = randomClickDelay(minCPS.getValue().intValue(), maxCPS.getValue().intValue());
    private long lastSwing = 0L;
    public long randomClickDelay(final int minCPS, final int maxCPS) {
        return (long) ((Math.random() * (1000 / minCPS - 1000 / maxCPS + 1)) + 1000 / maxCPS);
    }
    public Trigger() {
        super("Trigger", ModuleType.Combat);
        addValues(maxCPS,minCPS);
        addValues(select.getValues());
    }
    @EventTarget
    public void onRender(EventRender3D render3D) {
        val objectMouseOver = mc.getObjectMouseOver();

        if (!objectMouseOver.isNull() && System.currentTimeMillis() - lastSwing >= delay &&
                isValid(objectMouseOver.getEntityHit())) {
            KeyBinding.onTick(mc.getGameSettings().getKeyBindAttack().getKeyCode()); // Minecraft Click handling
            lastSwing = System.currentTimeMillis();
            delay = randomClickDelay(minCPS.getValue().intValue(), maxCPS.getValue().intValue());
        }
    }
    public boolean isValid(Entity entity){
        if (!EntityLivingBase.isEntityLivingBase(entity)) return false;
        if (entity.isNull()) return false;
        EntityPlayerSP thePlayer = mc.getThePlayer();
        if (thePlayer.isPlayerSleeping()
                || thePlayer.isDead()
                || thePlayer.getHealth() <= 0
                || entity.isDead()
                || EntityArmorStand.isEntityArmorStand(entity) || AntiBot.isEntityBot(entity)
                || EntityPlayerSP.isEntityPlayerSP(entity)) {
            return false;
        }
        if (!select.check(entity))return false;
        if (AntiBot.isEntityBot(entity)) return false;
        if (Teams.isTeam(entity)) return false;
        return true;
    }
}
