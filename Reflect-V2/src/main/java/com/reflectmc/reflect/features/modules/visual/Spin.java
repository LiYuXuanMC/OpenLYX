package com.reflectmc.reflect.features.modules.visual;

import com.reflectmc.reflect.event.annotation.EventTarget;
import com.reflectmc.reflect.event.events.player.EventPreUpdate;
import com.reflectmc.reflect.features.modules.Category;
import com.reflectmc.reflect.features.modules.Module;
import com.reflectmc.reflect.features.values.DoubleValue;
import com.reflectmc.reflect.utils.render.Translate;

public class Spin extends Module {
    public DoubleValue speed = new DoubleValue("Speed", 100, 1, 10, "0");

    public Translate translate = new Translate(0, 90);

    public Spin() {
        super("Spin", Category.Visual);
        addValues(speed);
    }

    @EventTarget
    public void eventPreUpdate(EventPreUpdate preUpdate) {
        translate.setX((float) (translate.getX() + speed.getValue()));
        preUpdate.setRotation(translate.getX(),translate.getY());
        if (translate.getX() >= 361) {
            translate.setX(1);
        }
    }
}
