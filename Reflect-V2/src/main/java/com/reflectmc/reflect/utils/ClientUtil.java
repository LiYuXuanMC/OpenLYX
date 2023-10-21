package com.reflectmc.reflect.utils;

import com.reflectmc.reflect.Reflect;
import com.reflectmc.reflect.features.modules.world.Timer;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.Minecraft;
import com.reflectmc.reflect.wrapper.wrappers.minecraft.utils.text.ChatComponentText;

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
                if (Reflect.getINSTANCE().getMetadata().getServerChannel() == 0)
                    printChat("§r§8[§r§9§lDEBUG§r§8]§r "+s);
                break;
        }
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
    public static void resetTimer(){
        Timer timer = (Timer) Reflect.getINSTANCE().getModuleManager().getModule(Timer.class);
        if (timer.isEnable()){
            Minecraft.getMinecraft().getTimer().setTimerSpeed(timer.speed.getValue().floatValue());
        } else {
            Minecraft.getMinecraft().getTimer().setTimerSpeed(1F);
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
