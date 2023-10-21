package al.logger.client.value.impls;

import al.logger.client.value.bases.Value;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.List;

public class MultipleColorValue extends Value<List<ColorValue>> {

    public MultipleColorValue(String name, ColorValue... value) {
        super(name, Arrays.asList(value));
    }

    public ColorValue getColorValue(String name) {
        return getValue().stream().filter(colorValue -> colorValue.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public String save() {
        JsonObject jsonBody = new JsonObject();
        for (ColorValue colorValue : getValue()) {
            jsonBody.addProperty(colorValue.getName(), colorValue.save());
        }
        return jsonBody.toString();
    }

    @Override
    public void load(String v) {
        JsonObject jsonObject = new Gson().fromJson(v, JsonObject.class);
        for (ColorValue colorValue : getValue()) {
            colorValue.load(jsonObject.get(colorValue.getName()).getAsString());
        }
    }

}
