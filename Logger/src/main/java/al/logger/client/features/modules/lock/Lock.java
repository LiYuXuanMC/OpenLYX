package al.logger.client.features.modules.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

public class Lock {
    @Getter
    private final Locks type;
    @Setter
    @Getter
    private boolean locked;
    public Lock(Locks type){
        this.type = type;
    }
}
