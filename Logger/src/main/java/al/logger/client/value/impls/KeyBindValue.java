package al.logger.client.value.impls;


import al.logger.client.utils.enums.EnumKey;
import al.logger.client.value.bases.Value;

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

