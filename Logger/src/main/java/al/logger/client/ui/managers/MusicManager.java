package al.logger.client.ui.managers;

import al.logger.client.Logger;
import al.logger.client.resource.ByteLocation;
import al.logger.client.resource.ResourceInfo;
import al.logger.client.ui.bases.components.QRCodeStatus;
import al.logger.client.utils.network.Connect;
import al.logger.client.utils.network.WebUtil;
import al.logger.client.wrapper.LoggerMC.utils.ResourceLocation;
import al.logger.libs.jaco.mp3.player.MP3Player;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MusicManager {

    @Getter
    @Setter
    private MP3Player mp3Player = new MP3Player();

    @Getter
    @Setter
    private WebUtil webUtil = new WebUtil("https://api.cxchenxu.space");

    @Getter
    @Setter
    private Connect cookie = new Connect("Cookie", "");

    @Getter
    @Setter
    private Connect user_agent = new Connect("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36 Edg/117.0.0.0");

    @Getter
    @Setter
    private String QRCodeKey = "";

    @Getter
    @Setter
    private String userId = "";

    @Getter
    @Setter
    private int volume = 30;

    public MusicManager() {
        this.mp3Player.setVolume(this.getVolume());
    }

    public void refreshQRCodeKey() {
        try {
            Gson gson = new Gson();
            JsonObject qrObject = gson.fromJson(new String(this.webUtil.get("/login/qr/key", user_agent), StandardCharsets.UTF_8), JsonObject.class);
            int code = qrObject.get("code").getAsInt();
            if (code == 200) {
                this.setQRCodeKey(qrObject.get("data").getAsJsonObject().get("unikey").getAsString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QRCodeStatus getQRCodeStatus() {
        try {
            Gson gson = new Gson();
            JsonObject qrObject = gson.fromJson(new String(this.webUtil.get("/login/qr/check?key=" + this.getQRCodeKey() + "&timestamp=" + System.currentTimeMillis(), user_agent), StandardCharsets.UTF_8), JsonObject.class);
            int code = qrObject.get("code").getAsInt();
            for (QRCodeStatus value : QRCodeStatus.values()) {
                if (value.getCode() == code) {
                    value.setMessage(qrObject.get("message").getAsString());
                    value.setCookie(qrObject.get("cookie").getAsString());
                    this.getCookie().setBody(value.getCookie());
                    return value;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return QRCodeStatus.QR_CODE_EXPIRED;
    }

    public ResourceLocation getQRCodeImage() {
        try {
            Gson gson = new Gson();
            JsonObject qrObject = gson.fromJson(new String(this.webUtil.get("/login/qr/create?key=" + this.getQRCodeKey() + "&qrimg=true", user_agent), StandardCharsets.UTF_8), JsonObject.class);
            String base64Image = qrObject.get("data").getAsJsonObject().get("qrimg").getAsString().replace("data:image/png;base64,", "");
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(base64Image)));
            return new ByteLocation(null, this.QRCodeKey).getResourceLocation(this.QRCodeKey, bufferedImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean visitorLogin() {
        try {
            Gson gson = new Gson();
            JsonObject qrObject = gson.fromJson(new String(this.webUtil.get("/register/anonimous", user_agent), StandardCharsets.UTF_8), JsonObject.class);
            int code = qrObject.get("code").getAsInt();
            if (code == 200) {
                this.setUserId(qrObject.get("userId").getAsString());
                this.getCookie().setBody(qrObject.get("cookie").getAsString());
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


}
