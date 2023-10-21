package al.nya.reflect.features.command.commands;

import al.nya.reflect.Reflect;
import al.nya.reflect.config.configs.WaypointsConfig;
import al.nya.reflect.features.command.Command;
import al.nya.reflect.features.modules.World.Waypoints;
import al.nya.reflect.features.modules.World.waypoint.WaypointManager;
import al.nya.reflect.utils.StringUtil;
import al.nya.reflect.utils.client.ClientUtil;
import al.nya.reflect.utils.math.MathUtil;
import al.nya.reflect.wrapper.wraps.wrapper.Timer;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayerSP;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.ServerData;
import al.nya.reflect.wrapper.wraps.wrapper.utils.ChatStyle;
import al.nya.reflect.wrapper.wraps.wrapper.utils.Vec3;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.HoverEvent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.HoverEventAction;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.click.ClickEvent;
import al.nya.reflect.wrapper.wraps.wrapper.utils.event.click.ClickEventAction;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.ChatComponentText;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.IChatComponent;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class WayPoints extends Command {
    public WayPoints() {
        super("WayPoints Command", "waypoints");
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onMessage(String input, String[] args) {
        if (!this.clamp(input, 2)) {
            this.printUsage();
            return;
        }

        ServerData serverData = mc.getCurrentServerData();
        EntityPlayerSP thePlayer = mc.getThePlayer();
        Entity ridingEntity = thePlayer.getRidingEntity();
        Timer timer = mc.getTimer();

        final String[] split = input.split(" ");

        final String host = !serverData.isNull() ? serverData.getServerIP() : "localhost";

        if (equals(new String[]{"Add", "A"}, split[1])) {
            if (!this.clamp(input, 3, 6)) {
                this.printUsage();
                return;
            }

            final String name = split[2];

            final Waypoints.WaypointData waypointData = WaypointManager.INSTANCE.find(host, name);

            if (waypointData != null) {
                ClientUtil.printChat("\247c\"" + name + "\"\247f is already a waypoint");
            } else {
                if (split.length > 3) {
                    if (!this.clamp(input, 6, 6)) {
                        this.printUsage();
                        return;
                    }
                    if (StringUtil.isDouble(split[3])) {
                        if (StringUtil.isDouble(split[4])) {
                            if (StringUtil.isDouble(split[5])) {
                                final DecimalFormat format = new DecimalFormat("#.#");
                                final String x = format.format(Double.parseDouble(split[3]));
                                final String y = format.format(Double.parseDouble(split[4]));
                                final String z = format.format(Double.parseDouble(split[5]));

                                ClientUtil.printChat("Added waypoint " + name + " at x:" + x + " y:" + y + " z:" + z);
                                WaypointManager.INSTANCE.getWaypointDataList().add(new Waypoints.WaypointData(host, name, thePlayer.getDimension(), Double.parseDouble(split[3]), Double.parseDouble(split[4]), Double.parseDouble(split[5])));
                                Reflect.Instance.configManager.save(WaypointsConfig.class);
                            } else {
                                ClientUtil.printChat(ClientUtil.Level.ERROR, "Unknown number " + "\247f\"" + split[5] + "\"");
                            }
                        } else {
                            ClientUtil.printChat(ClientUtil.Level.ERROR, "Unknown number " + "\247f\"" + split[4] + "\"");
                        }
                    } else {
                        ClientUtil.printChat(ClientUtil.Level.ERROR, "Unknown number " + "\247f\"" + split[3] + "\"");
                    }
                } else {
                    final DecimalFormat format = new DecimalFormat("#.#");
                    ClientUtil.printChat("Added waypoint " + name + " at x:" + format.format(thePlayer.getPosX()) + " y:" + format.format(thePlayer.getPosY() + thePlayer.getEyeHeight()) + " z:" + format.format(thePlayer.getPosZ()));
                    WaypointManager.INSTANCE.getWaypointDataList().add(new Waypoints.WaypointData(host, name, thePlayer.getDimension(), thePlayer.getPosX(), thePlayer.getPosY() + thePlayer.getEyeHeight(), thePlayer.getPosZ()));
                    Reflect.Instance.configManager.save(WaypointsConfig.class);
                }
            }
        } else if (equals(new String[]{"Remove", "R", "Rem", "Delete", "Del"}, split[1])) {
            if (!this.clamp(input, 3, 3)) {
                this.printUsage();
                return;
            }

            final String name = split[2];

            final Waypoints.WaypointData waypointData = WaypointManager.INSTANCE.find(host, name);

            if (waypointData != null) {
                ClientUtil.printChat("Removed waypoint \247c" + waypointData.getName() + " \247f");
                WaypointManager.INSTANCE.getWaypointDataList().remove(waypointData);
                Reflect.Instance.configManager.save(WaypointsConfig.class);
            } else {
                ClientUtil.printChat(ClientUtil.Level.ERROR, "Unknown waypoint " + "\247f\"" + name + "\"");
            }
        } else if (equals(new String[]{"List", "L"}, split[1])) {
            if (!this.clamp(input, 2, 2)) {
                this.printUsage();
                return;
            }

            int size = 0;

            for (Waypoints.WaypointData waypointData : WaypointManager.INSTANCE.getWaypointDataList()) {
                if (waypointData.getHost().equals(host)) {
                    size++;
                }
            }

            if (size > 0) {
                final IChatComponent msg = new ChatComponentText("\2477Waypoints for " + host + " [" + size + "]\247f ");

                final DecimalFormat format = new DecimalFormat("#.#");

                for (Waypoints.WaypointData data : WaypointManager.INSTANCE.getWaypointDataList()) {
                    if (data != null && data.getHost().equals(host)) {
                        msg.appendSibling(new ChatComponentText("\2477[\247a" + data.getName() + "\2477] ")
                                .setChatStyle(new ChatStyle()
                                        .setHoverEvent(new HoverEvent(HoverEventAction.SHOW_TEXT, new ChatComponentText("x: " + format.format(data.getX()) + " y: " + format.format(data.getY()) + " z: " + format.format(data.getZ()) + "\n" + "Dimension: " + data.getDimension() + "\n\247c\247lClick to learn more")))
                                        .setClickEvent(new ClickEvent(ClickEventAction.RUN_COMMAND, ".waypoints info " + data.getName()))));
                    }
                }
                mc.getIngameGUI().getGuiChat().printChatMessage(msg);
            } else {
                ClientUtil.printChat("You don't have any waypoints for " + host);
            }
        } else if (equals(new String[]{"Info", "I"}, split[1])) {
            if (!this.clamp(input, 3, 3)) {
                this.printUsage();
                return;
            }
            final DecimalFormat format = new DecimalFormat("#.#");

            final String name = split[2];
            final Waypoints.WaypointData waypointData = WaypointManager.INSTANCE.find(host, name);

            if (waypointData != null) {
                mc.getIngameGUI().getGuiChat().printChatMessage(new ChatComponentText("========================"));
                mc.getIngameGUI().getGuiChat().printChatMessage(new ChatComponentText("\247a" + name + "\247f is a waypoint for " + host));
                mc.getIngameGUI().getGuiChat().printChatMessage(new ChatComponentText("\247ax: " + waypointData.getX() + "\247a y: " + waypointData.getY() + "\247a z: " + waypointData.getZ() + "\247a Dimension: " + waypointData.getDimension()));
                mc.getIngameGUI().getGuiChat().printChatMessage(new ChatComponentText("\247aAdd Time: \247r" + sdf.format(waypointData.getTime()) + "\247f"));
                final IChatComponent msg = new ChatComponentText("Manage: ");
                msg.appendSibling(new ChatComponentText("\2477[\247c" + "Delete" + "\2477] ")
                        .setChatStyle(new ChatStyle()
                                .setHoverEvent(new HoverEvent(HoverEventAction.SHOW_TEXT, new ChatComponentText("\247c\247lClick to Delete")))
                                .setClickEvent(new ClickEvent(ClickEventAction.RUN_COMMAND, ".waypoints remove " + waypointData.getName()))));
                msg.appendSibling(new ChatComponentText("\2477[\247a" + "Teleport" + "\2477] ")
                        .setChatStyle(new ChatStyle()
                                .setHoverEvent(new HoverEvent(HoverEventAction.SHOW_TEXT, new ChatComponentText("\247c\247lClick to Teleport")))
                                .setClickEvent(new ClickEvent(ClickEventAction.RUN_COMMAND, ".tp " + Double.parseDouble(format.format(waypointData.getX())) + " " + Double.parseDouble(format.format(waypointData.getY())) + " " + Double.parseDouble(format.format(waypointData.getZ()))))));
                mc.getIngameGUI().getGuiChat().printChatMessage(msg);
                mc.getIngameGUI().getGuiChat().printChatMessage(new ChatComponentText("========================"));
            } else {
                ClientUtil.printChat(ClientUtil.Level.ERROR, "Unknown waypoint " + "\247f\"" + name + "\"");
            }

        } else if (equals(new String[]{"Angle", "Ang"}, split[1])) {
            if (!this.clamp(input, 3, 3)) {
                this.printUsage();
                return;
            }
            final String name = split[2];
            final Waypoints.WaypointData waypointData = WaypointManager.INSTANCE.find(host, name);

            if (waypointData != null) {
                float[] angle = MathUtil.calcAngle(thePlayer.getPositionEyes(timer.getRenderPartialTicks()), new Vec3(waypointData.getX(), waypointData.getY(), waypointData.getZ()));
                thePlayer.setRotationYaw(angle[0]);
                thePlayer.setRotationPitch(angle[1]);

                if (!ridingEntity.isNull()) {
                    ridingEntity.setRotationYaw(angle[0]);
                    ridingEntity.setRotationPitch(angle[1]);
                }

                final DecimalFormat format = new DecimalFormat("#.#");
                ClientUtil.printChat("Set Angles to " + format.format(angle[0]) + ", " + format.format(angle[1]));

            } else {
                ClientUtil.printChat(ClientUtil.Level.ERROR, "Unknown waypoint " + "\247f\"" + name + "\"");
            }
        } else if (equals(new String[]{"Clear", "C"}, split[1])) {
            if (!this.clamp(input, 2, 2)) {
                this.printUsage();
                return;
            }

            final int waypoints = WaypointManager.INSTANCE.getWaypointDataList().size();

            if (waypoints > 0) {
                ClientUtil.printChat("Removed \247c" + waypoints + "\247f waypoint" + (waypoints > 1 ? "s" : ""));
                WaypointManager.INSTANCE.getWaypointDataList().clear();
//                Reflect.Instance.configManager.save(WorldConfig.class);
            } else {
                ClientUtil.printChat("You don't have any waypoints");
            }
        } else {
            ClientUtil.printChat(ClientUtil.Level.ERROR, "Unknown input " + "\247f\"" + input + "\"");
        }
    }


    @Override
    public String[] getHelpMessage() {
        return new String[]{".waypoint add <name> <x> <y> <z>",
                ".waypoint remove <name>",
                ".waypoint list",
                ".waypoint info <name>",
                ".waypoint angle <name>",
                ".waypoint clear"};
    }
}
