package al.logger.client.value.impls;

import al.logger.client.value.bases.Value;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

public class TrackValue<V extends Number> extends Value<Double> {

    @Getter
    @Setter
    private double min;
    @Getter
    @Setter
    private double max;
    @Getter
    private double minValue;
    @Getter
    private double maxValue;
    @Getter
    @Setter
    private double inc;

    public TrackValue(String name, double min, double max, double minValue, double maxValue, double inc) {
        super(name, null);
        this.min = min;
        this.max = max;
        this.inc = inc;
        this.minValue = this.minValue < this.min ? this.min : minValue;
        this.maxValue = this.maxValue > this.max ? this.max : maxValue;
    }

    public void setMinValue(double minValue) {
        if (minValue < this.min) {
            this.minValue = this.min;
        } else {
            this.minValue = minValue;
        }
    }

    public void setMaxValue(double maxValue) {
        if (maxValue > this.max) {
            this.maxValue = this.max;
        } else {
            this.maxValue = maxValue;
        }
    }

    @Override
    public String save() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("MinValue", this.minValue);
        jsonObject.addProperty("MaxValue", this.maxValue);
        return jsonObject.toString();
    }

    @Override
    public void load(String v) {
        JsonObject jsonObject = new Gson().fromJson(v, JsonObject.class);
        this.minValue = jsonObject.get("MinValue").getAsDouble();
        this.maxValue = jsonObject.get("MaxValue").getAsDouble();
    }
}
