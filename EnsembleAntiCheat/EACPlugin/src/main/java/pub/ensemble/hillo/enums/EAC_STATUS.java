package pub.ensemble.hillo.enums;

import lombok.Getter;
import lombok.Setter;

public enum EAC_STATUS {

    HANDSHAKE(0),
    AUTHENTICATED(1)
    /*,
    FULL_VERIFY(2)
    */
    ;

    @Getter
    private final int value;

    EAC_STATUS(int value) {
        this.value = value;
    }

}
