package al.logger.client.ui.bases.components;

import by.radioegor146.nativeobfuscator.Native;
import lombok.Getter;
import lombok.Setter;

@Native
public class NextEx extends RuntimeException {
    @Getter
    @Setter
    private String note;
    public NextEx() {
        super("Render Screen");
    }
}
