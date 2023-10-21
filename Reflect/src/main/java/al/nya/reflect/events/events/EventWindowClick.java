package al.nya.reflect.events.events;

public class EventWindowClick extends Event {
    private final int windowId;
    private final int slotId;
    private final int mouseButtonClicked;
    private final int mode;

    public EventWindowClick(int windowId, int slotId, int mouseButtonClicked, int mode) {
        this.windowId = windowId;
        this.slotId = slotId;
        this.mouseButtonClicked = mouseButtonClicked;
        this.mode = mode;
    }

    public int getWindowId() {
        return windowId;
    }

    public int getSlotId() {
        return slotId;
    }

    public int getMouseButtonClicked() {
        return mouseButtonClicked;
    }

    public int getMode() {
        return mode;
    }
}
