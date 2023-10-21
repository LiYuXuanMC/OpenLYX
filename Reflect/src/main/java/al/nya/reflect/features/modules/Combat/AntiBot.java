package al.nya.reflect.features.modules.Combat;

import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleManager;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.value.ModeValue;
import al.nya.reflect.value.OptionValue;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.entity.Entity;
import al.nya.reflect.wrapper.wraps.wrapper.entity.EntityPlayer;
import al.nya.reflect.wrapper.wraps.wrapper.network.NetworkPlayerInfo;

public class AntiBot extends Module {
    public static ModeValue mode = new ModeValue("Mode",Mode.Hypixel,Mode.values());
    public static OptionValue ping = new OptionValue("Ping",true);
    public AntiBot() {
        super("AntiBot", "反假人", ModuleType.Combat);
        addValue(mode);
    }
    public static boolean isEntityBot(Entity entity) {
        if (!ModuleManager.getModuleByName("AntiBot").isEnable()) return false;
        Minecraft mc = Minecraft.getMinecraft();
        if (!(EntityPlayer.isEntityPlayer(entity))) {
            return false;
        }
        if (mc.getCurrentServerData() == null) {
            return false;
        }
        if (mode.getValue() == Mode.Hypixel && entity.getTicksExisted() <= 10 * 20)
            return false;


        return entity.getDisplayName().getFormattedText().toLowerCase().contains("npc") || entity.getDisplayName().getFormattedText().startsWith("\u0e22\u0e07") || !isOnTab(entity);
    }
//    @EventTarget
//    public void onTick(EventTick e) {
//        if (mode.getValue() == Mode.Hypixel) {
//            for (EntityPlayer entity : mc.getTheWorld().getPlayerEntities()) {
//                if (entity.getDistanceToEntity(mc.getThePlayer()) < 3.0 && entity.getWrapObject() != mc.getThePlayer().getWrapObject()) {
//                    ClientUtil.printChat(ClientUtil.Level.DEBUG, String.format("NE:%s OG:%s TK:%s IT:%s", entity.getDisplayName().getFormattedText(), entity.isOnGround(), entity.getTicksExisted(),isOnTab(entity)));
//                }
//            }
//        }
//    }

    enum Mode {
        Basic,
        Hypixel
    }
    public static boolean isOnTab(Entity entity) {
        Minecraft mc = Minecraft.getMinecraft();
        for (NetworkPlayerInfo npi : mc.getNetHandler().getPlayerInfoMap()) {
            if (npi.getGameProfile().isNull() || !npi.getGameProfile().getName().contains(entity.getName())) continue;
            return true;
        }
        return false;
    }
}
