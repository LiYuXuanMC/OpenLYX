package al.nya.reflect.features.command.commands;

import al.nya.reflect.features.command.Command;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.Player.UHCHelper;
import al.nya.reflect.gui.notification.NotificationPublisher;
import al.nya.reflect.gui.notification.NotificationType;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;

public class Teleport extends Command {
    public static double[] pos = new double[]{0, 120, 0};

    public Teleport() {
        super("UHC TP", "tp");
    }

    @Override
    public void onMessage(String msg, String[] args) {
        if (!ModuleManager.getModule(UHCHelper.class).isEnable()) {
            ClientUtil.printChat("Failure to teleport, please enable the UHCHelper module.");
            return;
        }
        if (args.length < 3) {
            if (args.length == 1) {
                for (EntityPlayer player : mc.getTheWorld().getPlayerEntities()) {
                    if (player.getName().equalsIgnoreCase(args[0])) {
                        final double y = UHCHelper.YMode.getValue() == UHCHelper.YMODE.TargetY ? player.getPosY() : UHCHelper.YMode.getValue() == UHCHelper.YMODE.L100 ? 100D : 200D;
                        pos = new double[]{player.getPosX(), y, player.getPosZ()};
                        new Thread(() -> {
                            mc.getThePlayer().sendChatMessage("/hub");
                            try {
                                Thread.sleep(500L);
                            } catch (InterruptedException ignored) {
                            }
                            mc.getThePlayer().sendChatMessage("/rejoin");
                            ClientUtil.printChat("Teleported to " + player.getName());
                        }).start();
                        return;
                    }
                }
                ClientUtil.printChat("Player not found");
                return;
            }
            ClientUtil.printChat("Usage: .tp [X] [Y] [Z] / [Player]");
            return;
        }

        try {
            pos = new double[]{Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2])};

            new Thread(() -> {
                mc.getThePlayer().sendChatMessage("/hub");
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException ignored) {
                }
                mc.getThePlayer().sendChatMessage("/rejoin");
                ClientUtil.printChat("Try to teleport to " + pos[0] + "," + pos[1] + "," + pos[2]);
            }).start();

        } catch (Exception e) {
            NotificationPublisher.queue("Teleport", "Error: " + e.getMessage(), NotificationType.ERROR);
        }

    }
}
