package al.nya.reflect.utils.client;

import al.nya.reflect.Reflect;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.Player.IRC;
import al.nya.reflect.features.modules.World.Timer;
import al.nya.reflect.socket.CommandIRCChat;
import al.nya.reflect.socket.packets.PacketIRCChat;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.ChatComponentText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClientUtil {
    public static void printChat(String s) {
        Minecraft mc = Minecraft.getMinecraft();
        mc.addScheduledTask(() -> {
            mc.getIngameGUI().getGuiChat().printChatMessage(new ChatComponentText("§l§b[§r§dReflect§r§l§b]§r" + s));
        });
    }

    public static void printChat(Level l, String s) {
        switch (l) {
            case INFO:
                printChat(" " + s);
                break;
            case WARNING:
                printChat("§r§8[§r§e§lWARNING§r§8]§r "+s);
                break;
            case ERROR:
                printChat("§r§8[§r§c§lERROR§r§8]§r "+s);
                break;
            case DEBUG:
                if (Reflect.debug)
                    printChat("§r§8[§r§9§lDEBUG§r§8]§r "+s);
                break;
        }
    }
    public static void resetTimer(){
        Timer timer = ModuleManager.getModule(Timer.class);
        if (timer.isEnable()){
            Minecraft.getMinecraft().getTimer().setTimerSpeed(timer.speed.getValue().floatValue());
        } else {
            Minecraft.getMinecraft().getTimer().setTimerSpeed(1F);
        }
    }

    public static void printIRC(CommandIRCChat ircChat) {
        if (!ModuleManager.getModule(IRC.class).isEnable()) return;
        Minecraft.getMinecraft().getIngameGUI().getGuiChat().printChatMessage(new ChatComponentText(
                "\u00a7e[IRC]\u00a76[" + ircChat.dst.rank + "]\u00a7a[" + ircChat.dst.name + "]\u00a77:" + ircChat.text
        ));
    }

    public static String execCmd(String[] cmd) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(cmd);
            String inStr = consumeInputStream(process.getInputStream());
            process.waitFor();
            return inStr;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String consumeInputStream(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        return sb.toString();
    }

    public enum Level {
        INFO,
        WARNING,
        ERROR,
        DEBUG
    }
}
