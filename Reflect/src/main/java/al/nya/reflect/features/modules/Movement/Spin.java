package al.nya.reflect.features.modules.Movement;

import al.nya.reflect.events.annotation.EventTarget;
import al.nya.reflect.events.events.EventPreUpdate;
import al.nya.reflect.features.modules.Module;
import al.nya.reflect.features.modules.ModuleType;
import al.nya.reflect.utils.render.Translate;
import al.nya.reflect.value.DoubleValue;

public class Spin extends Module {
    public DoubleValue speed = new DoubleValue("Speed", 100, 1, 10, "0");

    public Translate translate = new Translate(0, 90);

    public Spin() {
        super("Spin", ModuleType.Movement);
        addValues(speed);
    }

    @EventTarget
    public void eventPreUpdate(EventPreUpdate preUpdate) {
        translate.setX((float) (translate.getX() + speed.getValue()));
        preUpdate.setYaw(translate.getX());
        preUpdate.setPitch(translate.getY());
        if (translate.getX() >= 361) {
            translate.setX(1);
        }
    }
}
