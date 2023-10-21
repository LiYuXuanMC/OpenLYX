package al.logger.client.utils;

import al.logger.client.ui.bases.ComponentBase;
import al.logger.client.ui.bases.Position;
import al.logger.client.ui.bases.styles.Enums;

public class ScreenUtil {

    public static boolean isHovered(float x, float y, float width, float height, int mouseX, int mouseY) {
        width += x;
        height += y;
        if (mouseX >= x && mouseX <= width && mouseY >= y && mouseY <= height) {
            return true;
        }
        return false;
    }

    public static void calculatedPosition(ComponentBase value, Position position) {
        if (value.getStyles().getHorizontal() == Enums.HORIZONTAL.STRAIGHT) {
            //Left Margin
            value.getPosition().setX(position.getX() + value.getStyles().getMargin()[0]);
            value.getPosition().setWidth(position.getWidth() - (value.getStyles().getMargin()[0] + value.getStyles().getMargin()[2]));
        } else if (value.getStyles().getHorizontal() == Enums.HORIZONTAL.CENTER) {
            value.getPosition().setX(position.getX() + (position.getWidth() - value.getPosition().getWidth()) / 2);
        } else if (value.getStyles().getHorizontal() == Enums.HORIZONTAL.RIGHT) {
            value.getPosition().setX((position.getX() + position.getWidth()) - value.getPosition().getWidth() - value.getStyles().getMargin()[2]);
        } else if (value.getStyles().getHorizontal() == Enums.HORIZONTAL.LEFT) {
            value.getPosition().setX(position.getX() + value.getStyles().getMargin()[0]);
        } else if (value.getStyles().getHorizontal() == Enums.HORIZONTAL.NONE) {
        }
        if (value.getStyles().getVertical() == Enums.VERTICAL.STRAIGHT) {
            //Top Margin
            value.getPosition().setY(position.getY() + value.getStyles().getMargin()[1]);
            value.getPosition().setHeight(position.getHeight() - (value.getStyles().getMargin()[1] + value.getStyles().getMargin()[3]));
        } else if (value.getStyles().getVertical() == Enums.VERTICAL.CENTER) {
            value.getPosition().setY(position.getY() + (position.getHeight() - value.getPosition().getHeight()) / 2);
        } else if (value.getStyles().getVertical() == Enums.VERTICAL.BOTTOM) {
            value.getPosition().setY((position.getY() + position.getHeight()) - value.getPosition().getHeight() - value.getStyles().getMargin()[3]);
        } else if (value.getStyles().getVertical() == Enums.VERTICAL.TOP) {
            value.getPosition().setY(position.getY() + value.getStyles().getMargin()[1]);
        } else if (value.getStyles().getVertical() == Enums.VERTICAL.NONE) {
        }
    }
}
