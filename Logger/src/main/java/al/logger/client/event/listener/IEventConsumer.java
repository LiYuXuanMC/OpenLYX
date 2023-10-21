package al.logger.client.event.listener;

import lombok.Getter;

import java.util.function.Consumer;

public abstract class IEventConsumer<T> implements Consumer<T> {
    @Getter
    private int priority;
    public IEventConsumer(int priority){
        this.priority = priority;
    }
}
