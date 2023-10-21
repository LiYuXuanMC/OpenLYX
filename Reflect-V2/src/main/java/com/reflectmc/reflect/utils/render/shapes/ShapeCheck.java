package com.reflectmc.reflect.utils.render.shapes;

import com.reflectmc.reflect.utils.render.RenderUtil;

import java.awt.*;

public class ShapeCheck implements Shape {
    private Color color;
    public ShapeCheck(Color color){
        this.color = color;
    }
    @Override
    public void draw(int x,int y) {
        RenderUtil.drawCircle(x,y,11,0,360,color.getRGB());
    }
}
