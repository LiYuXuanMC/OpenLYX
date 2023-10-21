package al.logger.client.features.modules.lock;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LockManager {
    private List<Lock> locks = new CopyOnWriteArrayList<>();
    public LockManager(){
        for (Locks value : Locks.values()) {
            locks.add(new Lock(value));
        }
    }
    public Lock getLock(Locks type){
        for(Lock lock : locks){
            if(lock.getType() == type){
                return lock;
            }
        }
        return null;
    }
}
