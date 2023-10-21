package al.logger.client.ui.bases.components;

import lombok.Getter;
import lombok.Setter;

public enum QRCodeStatus {
    QR_CODE_EXPIRED(800),
    QR_CODE_NOT_SCAN(801),
    QR_CODE_WAIT_OK(802),
    QR_CODE_SUCCESS(803);

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private String cookie;
    
    QRCodeStatus(int code) {
        this.code = code;
    }

}
