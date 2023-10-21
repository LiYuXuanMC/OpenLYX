package al.nya.reflect.features.modules.Player;

import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.StringUtil;
import al.nya.reflect.wrapper.wraps.wrapper.GameSettings;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.world.WorldClient;

import java.util.ArrayList;

public class AutoLevelUp extends Module {
    Thread thread;

    public AutoLevelUp() {
        super("AutoLevelUp", "自动升级", ModuleType.Player);
    }

    @Override
    public void onEnable() {
        if (thread != null && thread.isAlive()) {
            thread.stop();
            thread = null;
        }
        thread = new AutoLevelUp.thread();
        thread.start();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (thread != null && thread.isAlive()) {
            thread.stop();
            thread = null;
        }
        super.onDisable();
    }


    class thread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    EntityPlayerSP player = mc.getThePlayer();
                    WorldClient world = mc.getTheWorld();
                    GameSettings settings = mc.getGameSettings();
                    if (player.isNull() || world.isNull() || settings.isNull()) {
                        Thread.sleep(1000);
                        continue;
                    }

                    if (world.getScoreboard().isNull()) {
                        Thread.sleep(1000);
                        player.sendChatMessage("/hub");
                        continue;
                    }

                    ArrayList<String> scoreboard = StringUtil.getScoreboardAsString();

                    if (scoreboard.size() == 0) {
                        Thread.sleep(5000);
                        player.sendChatMessage("/hub");
                        continue;
                    }

                    boolean flag = false;

                    for (String s : scoreboard) {
                        if (s.contains("已死亡")) {
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        player.sendChatMessage("/play duels_classic_duel");
                        Thread.sleep(5000);
                        continue;
                    }

                    for (String s : scoreboard) {
                        if (s.contains("经典决斗")) {
                            flag = true;
                            break;
                        }
                    }

                    if (flag) {
                        settings.getKeyBindJump().setPressed(true);
                        settings.getKeyBindForward().setPressed(true);
                        Thread.sleep(5000);
                        continue;
                    }

                    player.sendChatMessage("/play duels_classic_duel");
                    Thread.sleep(5000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
