package al.logger.client.value.impls;

import al.logger.client.utils.animation.descricle.Animation;
import al.logger.client.value.bases.Value;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public class ColorValue extends Value<Color> {

    @Getter
    @Setter
    private float hue;

    @Getter
    @Setter
    private float saturation;

    @Getter
    @Setter
    private float brightness;

    @Getter
    @Setter
    private int alpha;


    public ColorValue(String name, Color value) {
        super(name, value);
        float[] hsb = new float[3];
        Color.RGBtoHSB(value.getRed(), value.getGreen(), value.getBlue(), hsb);
        this.hue = hsb[0];
        this.saturation = hsb[1];
        this.brightness = hsb[2];
        this.alpha = value.getAlpha();
    }

    @Override
    public void setValue(Color value) {
        super.setValue(value);
        float[] hsb = new float[3];
        Color.RGBtoHSB(value.getRed(), value.getGreen(), value.getBlue(), hsb);
        this.hue = hsb[0];
        this.saturation = hsb[1];
        this.brightness = hsb[2];
    }

    @Override
    public Color getValue() {
        Color color = Color.getHSBColor(this.hue, this.saturation, this.brightness);
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), this.alpha);
    }

    @Override
    public String save() {
        JsonObject jsonObject = new JsonObject();
        JsonObject colorJsonObject = new JsonObject();
        colorJsonObject.addProperty("Red", this.getValue().getRed());
        colorJsonObject.addProperty("Green", this.getValue().getGreen());
        colorJsonObject.addProperty("Blue", this.getValue().getBlue());
        colorJsonObject.addProperty("Alpha", this.getValue().getAlpha());
        jsonObject.addProperty("Color", new Gson().toJson(colorJsonObject));
        return jsonObject.toString();
    }

    @Override
    public void load(String v) {
        JsonObject jsonObject = new Gson().fromJson(v, JsonObject.class);
        JsonObject colorJsonObject = new Gson().fromJson(jsonObject.get("Color").getAsString(), JsonObject.class);
        this.setValue(new Color(colorJsonObject.get("Red").getAsInt(), colorJsonObject.get("Green").getAsInt(), colorJsonObject.get("Blue").getAsInt(), colorJsonObject.get("Alpha").getAsInt()));
    }
}
