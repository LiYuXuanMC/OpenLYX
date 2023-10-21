package al.nya.reflect.events.events;

public class EventMouse extends Event {
    private Button button;

    public EventMouse(Button button) {
        this.button = button;
    }

    public Button getButton() {
        return this.button;
    }

    public static enum Button {
        Left,
        Right,
        Middle;

    }

}
