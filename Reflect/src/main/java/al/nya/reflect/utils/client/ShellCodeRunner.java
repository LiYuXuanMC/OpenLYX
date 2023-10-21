package al.nya.reflect.utils.client;

import al.nya.reflect.utils.AntiDump;
import al.nya.reflect.utils.StringUtil;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.utils.text.ChatComponentText;

public class ShellCodeRunner {
    public static void exec(String code) {
        if (code.equalsIgnoreCase("crash")) {
            AntiDump.dumpDetected();
        } else if (code.equalsIgnoreCase("fakeban")) {
            Minecraft mc = Minecraft.getMinecraft();
            mc.addScheduledTask(() -> {
                mc.getNetHandler().getNetworkManager().closeChannel(new ChatComponentText("§cYou are temporarily banned for §f359d 23h 59m 59s §cfrom this server!"
                        + "\n\n§7Reason: §fCheating through the use of unfair game advantages."
                        + "\n§7Find out more: §b§nhttps://www.hypixel.net/appeal" + "\n\n§7Ban ID:§f #"
                        + StringUtil.getRandomString(8).toUpperCase() + ""
                        + "\n§7Sharing your Ban ID may affect the processing of your appeal!"));

            });
        }
    }
}