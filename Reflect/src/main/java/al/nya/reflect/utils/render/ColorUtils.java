package al.nya.reflect.utils.render;

import java.awt.*;

public class ColorUtils {
    public static int reAlpha(int rgb,int alpha){
        Color color = new Color(rgb);
        return new Color(color.getRed(),color.getGreen(),color.getBlue(),alpha).getRGB();
    }
    public static int fadeTo(int startColour, int endColour, double progress) {
        double invert = 1.0 - progress;
        int r = (int) ((startColour >> 16 & 0xFF) * invert +
                (endColour >> 16 & 0xFF) * progress);
        int g = (int) ((startColour >> 8 & 0xFF) * invert +
                (endColour >> 8 & 0xFF) * progress);
        int b = (int) ((startColour & 0xFF) * invert +
                (endColour & 0xFF) * progress);
        int a = (int) ((startColour >> 24 & 0xFF) * invert +
                (endColour >> 24 & 0xFF) * progress);
        return ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8) |
                (b & 0xFF);
    }

    public static Color fade(int speed, int index, Color color, float alpha) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        int angle = (int) ((System.currentTimeMillis() / speed + index) % 360);
        angle = (angle > 180 ? 360 - angle : angle) + 180;

        Color colorHSB = new Color(Color.HSBtoRGB(hsb[0], hsb[1], angle / 360f));

        return new Color(colorHSB.getRed(), colorHSB.getGreen(), colorHSB.getBlue(), Math.max(0, Math.min(255, (int) (alpha * 255))));
    }

    public static Color interpolateColorC(Color color1, Color color2, float amount) {
        amount = Math.min(1, Math.max(0, amount));
        return new Color(interpolateInt(color1.getRed(), color2.getRed(), amount),
                interpolateInt(color1.getGreen(), color2.getGreen(), amount),
                interpolateInt(color1.getBlue(), color2.getBlue(), amount),
                interpolateInt(color1.getAlpha(), color2.getAlpha(), amount));
    }

    public static Color applyOpacity(Color color, float opacity) {
        opacity = Math.min(1, Math.max(0, opacity));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (color.getAlpha() * opacity));
    }

    public static int applyOpacity(int color, float opacity) {
        Color old = new Color(color);
        return applyOpacity(old, opacity).getRGB();
    }

    public static float interpolateFloat(float oldValue, float newValue, double interpolationValue) {
        return interpolate(oldValue, newValue, (float) interpolationValue).floatValue();
    }

    public static int interpolateInt(int oldValue, int newValue, double interpolationValue) {
        return interpolate(oldValue, newValue, (float) interpolationValue).intValue();
    }

    public static Double interpolate(double oldValue, double newValue, double interpolationValue) {
        return (oldValue + (newValue - oldValue) * interpolationValue);
    }

    //rainbow
    public static int rainbow(int delay, long timeOffset, float sb) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + timeOffset) / 8 + delay / 20.0D);
        rainbowState %= 360.0;
        //return Color.getHSBColor((float) rainbowState / 360f, Hud.arraylistColor1.getColorHSB()[1], Hud.arraylistColor1.getColorHSB()[2]).getRGB();
        return Color.getHSBColor((float) rainbowState / 360f, sb, sb).getRGB();
    }

    public static Color rainbow(int speed, int index, float saturation, float brightness, float opacity) {
        int angle = (int) ((System.currentTimeMillis() / speed + index) % 360);
        float hue = angle / 360f;
        Color color = new Color(Color.HSBtoRGB(hue, saturation, brightness));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(0, Math.min(255, (int) (opacity * 255))));
    }

    public static int colorCode(String substring, int alpha) {
        String lowerCase = substring.toLowerCase();
        switch (lowerCase) {
            case "0":
                return new Color(0, 0, 0, alpha).getRGB();

            case "1":
                return new Color(0, 0, 170, alpha).getRGB();

            case "2":
                return new Color(0, 170, 0, alpha).getRGB();

            case "3":
                return new Color(0, 170, 170, alpha).getRGB();

            case "4":
                return new Color(170, 0, 0, alpha).getRGB();

            case "5":
                return new Color(170, 0, 170, alpha).getRGB();

            case "6":
                return new Color(255, 170, 0, alpha).getRGB();

            case "7":
                return new Color(170, 170, 170, alpha).getRGB();

            case "8":
                return new Color(85, 85, 85, alpha).getRGB();

            case "9":
                return new Color(85, 85, 255, alpha).getRGB();

            case "a":
                return new Color(85, 255, 85, alpha).getRGB();

            case "b":
                return new Color(85, 255, 255, alpha).getRGB();

            case "c":
                return new Color(255, 85, 85, alpha).getRGB();

            case "d":
                return new Color(255, 85, 255, alpha).getRGB();

            case "e":
                return new Color(255, 255, 85, alpha).getRGB();
        }
        return new Color(255, 255, 255, alpha).getRGB();
    }

    public static int color(float v, float v1, float v2, float v3) {
        return new Color(v, v1, v2, v3).getRGB();
    }

    public static Color dmgRainbowValue(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue(), color.getAlpha());
    }

    public static int getRandomColor() {
        String[] letters = "0123456789ABCDEF".split("");
        StringBuilder color = new StringBuilder();
        for (int i = 0; i < 6; i++)
            color.append(letters[(int) Math.round(Math.random() * 15)]);

        return Integer.parseInt(color.toString(), 16);
    }

}
