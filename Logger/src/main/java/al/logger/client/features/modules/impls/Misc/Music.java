package al.logger.client.features.modules.impls.Misc;

import al.logger.client.Logger;
import al.logger.client.event.annotation.Listener;
import al.logger.client.event.client.render.EventRender2D;
import al.logger.client.features.modules.Category;
import al.logger.client.features.modules.Module;

public class Music extends Module {
    public Music() {
        super("Music", "Music(Gui To MusicApp)", Category.Misc);
    }

    @Listener
    public void onRender2D(EventRender2D eventRender2D) {
    }


}
