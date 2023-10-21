package pub.ensemble.hillo.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import pub.ensemble.hillo.EACCore;
import pub.ensemble.hillo.enums.EAC_STATUS;
import pub.ensemble.hillo.messages.components.SPacketHandShake;
import pub.ensemble.hillo.messages.components.SPacketPing;
import pub.ensemble.hillo.utils.RandomUtil;
import pub.ensemble.hillo.utils.VerifyUtil;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class WrapEntity {

    @Getter
    private final Player player;

    @Getter
    @Setter
    private long lastJoinTime = 0;

    @Getter
    @Setter
    private EAC_STATUS status = EAC_STATUS.HANDSHAKE;

    @Getter
    @Setter
    private int processCount = 0;

    @Getter
    @Setter
    private String handShakeKey;

    @Getter
    @Setter
    private String handShakeKey16;

    @Getter
    @Setter
    private String encryptHandShakeKey = "Please use VerifyUtil.getSha256() to get the value of this field.";

    @Getter
    @Setter
    private String encryptHandShakeKey16 = "Please use VerifyUtil.getSha256() to get the value of this field.";

    public WrapEntity(Player player) {
        this.player = player;
        this.lastJoinTime = System.currentTimeMillis();
        this.handShakeKey = RandomUtil.getRandomString(6) + UUID.randomUUID();
        this.handShakeKey16 = handShakeKey.substring(0, 16);
        try {
            this.encryptHandShakeKey = VerifyUtil.getSha256(this.handShakeKey + "JtX2Z3Y4OmR5U6aV7bW8cX9dY0eA1fB2");
            this.encryptHandShakeKey16 = encryptHandShakeKey.substring(0, 16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        EACCore.INSTANCE.getEacHandler().sendPacket(this.getPlayer(), new SPacketHandShake(this.getHandShakeKey()));
    }

    public void onProcessPlayers() {
        this.setProcessCount(this.getProcessCount() + 1);
        if (this.getProcessCount() <= 1) {
            return;
        }
        if (this.getProcessCount() % 10 == 0 && this.getStatus() == EAC_STATUS.HANDSHAKE) {
            this.notUsingEAC();
            return;
        }else if(this.getProcessCount() % 10 == 0){
            EACCore.INSTANCE.getEacHandler().sendPacket(this.getPlayer(), new SPacketPing());
        }
        // 40 seconds
        if (this.getProcessCount() % 15 == 0 && System.currentTimeMillis() - this.getLastJoinTime() > 20000) {
            this.notUsingEAC();
            return;
        }
    }

    private void notUsingEAC() {
        this.getPlayer().kickPlayer("反作弊校验错误！请重启客户端");
    }

}
