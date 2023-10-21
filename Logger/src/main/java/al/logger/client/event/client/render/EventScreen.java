package al.logger.client.event.client.render;

import al.logger.client.event.Event;
import al.logger.client.wrapper.LoggerMC.gui.GuiScreen;
import lombok.Getter;

public class EventScreen implements Event {
    @Getter
    GuiScreen screen;

    public EventScreen(GuiScreen screen){
        this.screen = screen;
    }
}
