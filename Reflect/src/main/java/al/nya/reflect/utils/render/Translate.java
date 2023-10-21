package al.nya.reflect.utils.render;

public final class Translate {
    private float x;
    private float y;

    public Translate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void interpolate(float targetX, float targetY, double smoothing) {
        this.x = AnimationUtils.getAnimationState(this.x, targetX,
                (float) (Math.max(10, (Math.abs(this.x - targetX)) * 35) * 0.3));
        this.y = AnimationUtils.getAnimationState(this.y, targetY,
                (float) (Math.max(10, (Math.abs(this.y - targetY)) * 35) * 0.3));
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }
}

