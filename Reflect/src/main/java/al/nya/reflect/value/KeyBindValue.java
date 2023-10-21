package al.nya.reflect.value;

import al.nya.reflect.key.EnumKey;

public class KeyBindValue extends Value<EnumKey> {
    public KeyBindValue(String name, EnumKey value) {
        super(name, value);
    }

    @Override
    public String save() {
        return String.valueOf(getValue().getKeyCode());
    }

    @Override
    public void load(String v) {
        setValue(EnumKey.getKey(Integer.parseInt(v)));
    }
}
