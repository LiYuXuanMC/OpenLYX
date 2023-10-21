package al.logger.client.ui.bases.styles;

import lombok.Getter;

public class NormalStyle {

    @Getter
    private Enums.VERTICAL Vertical = Enums.VERTICAL.TOP;

    @Getter
    private Enums.HORIZONTAL Horizontal = Enums.HORIZONTAL.LEFT;

    @Getter
    private float[] Margin = new float[]{0, 0, 0, 0}; // Left Top Right Bottom

    public NormalStyle setVertical(Enums.VERTICAL vertical) {
        Vertical = vertical;
        return this;
    }

    public NormalStyle setHorizontal(Enums.HORIZONTAL horizontal) {
        Horizontal = horizontal;
        return this;
    }

    public NormalStyle setMargin(float... vars) {
        //最大只能是4个参数
        int size = Math.min(vars.length, 4);
        for (int i = 0; i < size; i++) {
            Margin[i] = vars[i];
        }
        return this;
    }
}
