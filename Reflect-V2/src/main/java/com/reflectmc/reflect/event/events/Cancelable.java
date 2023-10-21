package com.reflectmc.reflect.event.events;

public interface Cancelable {
    void cancel();
    boolean isCancel();
}
