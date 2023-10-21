package al.logger.client.event.client.player;


import al.logger.client.event.Event;
import al.logger.client.utils.obfuscate.annotation.ExportObfuscate;

public class EventPlayerState implements Event {
    private boolean sprint;
    private boolean snake;
    public EventPlayerState(boolean sprint,boolean snake) {
        this.sprint = sprint;
        this.snake = snake;
    }
    @ExportObfuscate(name = "isSnake")
    public boolean isSnake() {
        return snake;
    }

    public void setSnake(boolean snake) {
        this.snake = snake;
    }

    public void setSprint(boolean sprint) {
        this.sprint = sprint;
    }
    @ExportObfuscate(name = "isSprint")
    public boolean isSprint() {
        return sprint;
    }
}
