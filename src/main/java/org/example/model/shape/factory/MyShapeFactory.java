package org.example.model.shape.factory;

import org.example.model.MyShape;
import org.example.model.shape.fill.FillBehavior;

import java.awt.*;

public class MyShapeFactory {

    public MyShape createShape(ShapeType type, Color color, FillBehavior fb) {

        MyShape shape = new MyShape(type.createShape(),color, fb);
        return shape;
    }
}
