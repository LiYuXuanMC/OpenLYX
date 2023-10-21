package al.logger.client.utils.animation.logger;

import lombok.Getter;
import lombok.Setter;

public class Smoother {
    private double value;
    private double timeConstant;
    private Long timestamp;
    @Setter
    @Getter
    private double target;

    public Smoother(double value, double timeConstant) {
        this.value = value;
        this.timeConstant = timeConstant;
    }

    public void update(double newValue, long currentTime) {
        if (timestamp == null) {
            value = newValue;
            timestamp = currentTime;
        } else {
            double timeDiff = currentTime - timestamp;
            double alpha = 1 - Math.pow(2, -timeDiff / timeConstant);
            value = alpha * newValue + (1 - alpha) * value;
            timestamp = currentTime;
        }
    }

    public void update(double newValue) {
        long currentTime = System.currentTimeMillis();
        if (timestamp == null) {
            value = newValue;
            timestamp = currentTime;
        } else {
            double timeDiff = currentTime - timestamp;
            double alpha = 1 - Math.pow(2, -timeDiff / timeConstant);
            value = alpha * newValue + (1 - alpha) * value;
            timestamp = currentTime;
        }
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        if (timestamp == null) {
            value = target;
            timestamp = currentTime;
        } else {
            double timeDiff = currentTime - timestamp;
            double alpha = 1 - Math.pow(2, -timeDiff / timeConstant);
            value = alpha * target + (1 - alpha) * value;
            timestamp = currentTime;
        }
    }

    public double get() {
        return value;
    }

    public double getTimeConstant() {
        return timeConstant;
    }

    public void setTimeConstant(double timeConstant) {
        this.timeConstant = timeConstant;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
