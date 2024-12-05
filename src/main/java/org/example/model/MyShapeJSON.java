package org.example.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.awt.*;
import java.awt.geom.RectangularShape;

@JsonAutoDetect
public class MyShapeJSON implements Cloneable{
    public Color color;
    public int x;
    public int y;
    public int w;
    public int h;
    public int idShapeType;
//    public ShapeType type;
    public boolean isFilled;

    public MyShapeJSON() {
    }

    public MyShapeJSON(Color color, int x, int y, int w, int h, int idShapeType, boolean isFilled) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.idShapeType = idShapeType;
        this.isFilled = isFilled;
    }
}
