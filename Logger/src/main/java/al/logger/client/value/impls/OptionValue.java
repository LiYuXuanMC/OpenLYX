package al.logger.client.value.impls;

import al.logger.client.value.bases.Value;
import lombok.Getter;
import lombok.Setter;

public class OptionValue extends Value<Boolean> {
    @Getter
    @Setter
    private int keyCode = 0;
    public OptionValue(String name, Boolean value) {
        super(name, value);
    }

    @Override
    public String save() {
        return getValue().toString();
    }

    @Override
    public void load(String v) {
        setValue(Boolean.parseBoolean(v));
    }
}
