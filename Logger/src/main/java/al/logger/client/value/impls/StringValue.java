package al.logger.client.value.impls;

import al.logger.client.value.bases.Value;

public class StringValue extends Value<String> {
    public StringValue(String name, String value) {
        super(name, value);
    }

    @Override
    public String save() {
        return getValue();
    }

    @Override
    public void load(String v) {
        setValue(v);
    }
}
