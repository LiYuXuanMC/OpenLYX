package al.logger.client.utils.anticrack;

import lombok.Getter;
import lombok.Setter;

public class ClassInfo {
    @Getter
    private LString md5;
    @Getter
    private int length;
    public ClassInfo(int length, LString md5){
        this.length = length;
        this.md5 = md5;
    }
}
