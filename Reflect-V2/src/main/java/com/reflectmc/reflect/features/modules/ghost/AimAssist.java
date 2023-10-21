package com.reflectmc.reflect.features.modules.ghost;

import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.player.EventLivingUpdate;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.modules.combat.AntiBot;
import com.reflectmc.reflect.features.values.DoubleValue;
import com.reflectmc.reflect.features.values.ModeValue;
import com.reflectmc.reflect.features.values.OptionValue;
import com.reflectmc.reflect.utils.EntitySelector;
import com.reflectmc.reflect.utils.rotation.Rotation;
import com.reflectmc.reflect.utils.rotation.RotationUtils;
import com.reflectmc.reflect.utils.timer.MSTimer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.Entity;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.entity.EntityPlayerSP;

import java.util.Optional;

public class AimAssist extends Module {

    private DoubleValue rangeValue = new DoubleValue("Range", 8D, 1D, 4.4D,"0.0");
    private DoubleValue turnSpeedValue = new DoubleValue("TurnSpeed", 180D, 1D, 2D,"0");
    private DoubleValue fovValue = new DoubleValue("FOV", 360F, 1F, 180F,"0");
    private OptionValue centerValue = new OptionValue("Center", false);
    private ModeValue aimMode = new ModeValue("AimMode",AimMode.Click,AimMode.values());
    private EntitySelector selector = new EntitySelector("Target");

    private MSTimer clickTimer = new MSTimer();

    public AimAssist() {
        super("AimAssist",Category.Ghost);
        addValues(rangeValue,turnSpeedValue,fovValue,centerValue,aimMode,selector.getValue());
    }

    @EventTarget
    public void onUpdate(EventLivingUpdate update){
        if (aimMode.getValue() == AimMode.Click && !mc.getGameSettings().getKeyBindAttack().isKeyDown()) {
            return;
        }
        double range = rangeValue.getValue();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        Optional optional = mc.getTheWorld().getLoadedEntityList().stream()
                .filter(it -> selector.check(it) && !AntiBot.isEntityBot(it) && thePlayer.canEntityBeSeen(it) && thePlayer.getDistanceToEntity(it) <= range && RotationUtils.getRotationDifference(it) <= fovValue.getValue())
                .min((it, it2) -> (int) (RotationUtils.getRotationDifference(it) - RotationUtils.getRotationDifference(it2)));
        if (!optional.isPresent()) return;
        Entity entity = (Entity) optional.get();
        if (entity.isNull()) return;
        Rotation rotation = RotationUtils.limitAngleChange(
                new Rotation(thePlayer.getRotationYaw(), thePlayer.getRotationPitch()),
                centerValue.getValue() ?
                        RotationUtils.toRotation(RotationUtils.getCenter(entity.getEntityBoundingBox()), true)
                        :
                        RotationUtils.searchCenter(entity.getEntityBoundingBox(), false, false, true, false).getRotation()
                , (float) (turnSpeedValue.getValue() + Math.random()));


        rotation.toPlayer(thePlayer);
    }

    enum AimMode {
        Lock,
        Click
    }
}
