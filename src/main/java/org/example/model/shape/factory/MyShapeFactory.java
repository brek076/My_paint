package org.example.model.shape.factory;

import org.example.model.MyShape;
import org.example.model.shape.fill.FillBehavior;
import org.example.model.shape.fill.NoFill;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class MyShapeFactory {

    public MyShape createShape(ShapeType type, Color color, FillBehavior fb) {
        if(color == null)
            color = Color.BLUE;
        if(fb == null)
            fb = new NoFill();

        return new MyShape(color, type.createShape(),fb);
    }
}
