package cc.systemv.rave.feature.module.modules.movement;

import cc.systemv.rave.event.annotation.Listener;
import cc.systemv.rave.event.events.EventNoSlow;
import cc.systemv.rave.feature.module.Category;
import cc.systemv.rave.feature.module.Module;

public class NoSlow extends Module {
    public NoSlow() {
        super("NoSlow", Category.Movement);
    }
    @Listener
    public void onNoSlow(EventNoSlow noSlow){
        noSlow.setSlow(false);
    }
}
