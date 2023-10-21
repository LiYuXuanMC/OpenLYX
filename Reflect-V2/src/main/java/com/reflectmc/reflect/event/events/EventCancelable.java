package com.reflectmc.reflect.event.events;

import com.reflectmc.reflect.event.Event;
import com.reflectmc.reflect.obfuscate.ExportObfuscate;

public class EventCancelable extends Event implements Cancelable {
    private boolean cancel = false;
    @Override
    public void cancel() {
        this.cancel = true;
    }

    @Override
    @ExportObfuscate(name = "isCancel")
    public boolean isCancel() {
        return cancel;
    }
}
