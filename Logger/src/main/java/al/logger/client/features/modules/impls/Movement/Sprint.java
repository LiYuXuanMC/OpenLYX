package al.logger.client.features.modules.impls.Movement;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.EventTick;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;
import al.logger.client.features.modules.impls.World.Scaffold;
import al.logger.client.wrapper.LoggerMC.GameSettings;


public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Keep your sprinting", Category.Movement);
    }

    @Listener
    public void onTick(EventTick eventTick){
        GameSettings gameSettings = mc.getGameSettings();
        if(gameSettings.getKeyBindForward().isKeyDown() && !mc.getThePlayer().isCollidedHorizontally()){
            gameSettings.getKeyBindSprint().setPressed(true);
        }
        if (Logger.getInstance().moduleManager.getModule(Scaffold.class).isEnable() && Scaffold.sprintMode.getValue() == Scaffold.SprintMode.None) {
            mc.getThePlayer().setSprinting(false);
        }
    }

    @Override
    public void onDisable() {
        mc.getThePlayer().setSprinting(false);
        mc.getGameSettings().getKeyBindSprint().setPressed(false);
        super.onDisable();
    }
}
