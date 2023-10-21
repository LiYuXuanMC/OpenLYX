package com.reflectmc.reflect.features.modules.ghost;

import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.player.EventLivingUpdate;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.MinMaxValue;
import com.reflectmc.reflect.features.values.OptionValue;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.KeyBinding;

public class AutoClicker extends Module {
    private OptionValue left = new OptionValue("LeftClick",true);
    private MinMaxValue leftCPS = new MinMaxValue("LeftCPS",5D,8D,1D,40D,"0"){
        @Override
        public boolean show(){
            return left.getValue();
        }
    };
    private OptionValue right = new OptionValue("RightClick",true);
    private MinMaxValue rightCPS = new MinMaxValue("RightCPS",5D,8D,1D,40D,"0"){
        @Override
        public boolean show(){
            return right.getValue();
        }
    };

    private long rightLastSwing = 0L;
    private long leftLastSwing = 0L;
    private long rightDelay = randomClickDelay(leftCPS.getValue()[0].intValue(),leftCPS.getValue()[1].intValue());
    private long leftDelay = randomClickDelay(rightCPS.getValue()[0].intValue(),rightCPS.getValue()[1].intValue());

    public AutoClicker() {
        super("AutoClicker",Category.Ghost);
        addValues(left,leftCPS,right,rightCPS);
    }

    @EventTarget
    public void onUpdate(EventLivingUpdate update){
        if (mc.getGameSettings().getKeyBindAttack().isKeyDown() && left.getValue() &&
                System.currentTimeMillis() - leftLastSwing >= leftDelay && mc.getPlayerController().getCurBlockDamageMP() == 0F) {
            mc.setLeftClickCounter(0);
            KeyBinding.onTick(mc.getGameSettings().getKeyBindAttack().getKeyCode()); // Minecraft Click Handling
            leftLastSwing = System.currentTimeMillis();
            leftDelay = randomClickDelay(leftCPS.getValue()[0].intValue(),leftCPS.getValue()[1].intValue());
        }

        // Right click
        if (mc.getGameSettings().getKeyBindUseItem().isKeyDown() && !mc.getThePlayer().isUsingItem() && right.getValue() &&
                System.currentTimeMillis() - rightLastSwing >= rightDelay) {
            KeyBinding.onTick(mc.getGameSettings().getKeyBindUseItem().getKeyCode()); // Minecraft Click Handling
            rightLastSwing = System.currentTimeMillis();
            rightDelay = randomClickDelay(rightCPS.getValue()[0].intValue(),rightCPS.getValue()[1].intValue());
        }
    }
    public long randomClickDelay(final int minCPS, final int maxCPS) {
        return (long) ((Math.random() * (1000 / minCPS - 1000 / maxCPS + 1)) + 1000 / maxCPS);
    }
}
