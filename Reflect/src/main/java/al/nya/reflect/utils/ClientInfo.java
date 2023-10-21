package al.nya.reflect.utils;

import al.nya.reflect.utils.client.EnvDetector;
import al.nya.reflect.utils.client.MargeleAntiCheatDetector;
import al.nya.reflect.utils.client.NACFucker;
import al.nya.reflect.wrapper.wraps.wrapper.Minecraft;
import al.nya.reflect.wrapper.wraps.wrapper.multiplayer.ServerData;
import lombok.Getter;

import java.awt.*;

public class ClientInfo {
    @Getter
    private String clientVersion;
    @Getter
    private boolean vanilla = false;
    @Getter
    private long startTime = 0;
    @Getter
    private String serverIP = null;
    @Getter
    private String AntiCheat = "";
    @Getter
    private SupportType supportType;

    public ClientInfo() {
        if (EnvDetector.is189Srg()) {
            clientVersion = "1.8.9";
            supportType = SupportType.Supported;
        }
        if (EnvDetector.is189Notch()) {
            clientVersion = "1.8.9";
            vanilla = true;
            supportType = SupportType.Supported;
        }
        if (EnvDetector.is1122Srg()) {
            clientVersion = "1.12.2";
            supportType = SupportType.Unsupported;
        }
        startTime = System.currentTimeMillis();
    }

    public void detectServer() {
        ServerData serverData = Minecraft.getMinecraft().getCurrentServerData();
        if (!serverData.isNull()) {
            serverIP = serverData.getServerIP();
        }
        AntiCheat = "";
        if (serverIP != null) {
            if (serverIP.contains("hypixel")) {
                AntiCheat += "WatchDog";
            }
        }
        if (MargeleAntiCheatDetector.isMAC()) {
            AntiCheat += "Margele's AntiCheat";
        }
        if (NACFucker.getNAC() != null) {
            AntiCheat += " NAC";
        }
    }

    enum SupportType {
        Supported(Color.RED.getRGB()),
        Unsupported(Color.GREEN.getRGB());
        @Getter
        private int color;

        private SupportType(int color) {
            this.color = color;
        }
    }
}
