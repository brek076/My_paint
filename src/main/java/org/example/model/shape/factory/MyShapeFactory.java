package org.example.model.shape.factory;

import org.example.model.MyShape;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;
import org.example.model.shape.fill.NoFill;

import java.awt.*;

public class MyShapeFactory {

    public MyShape createShape(ShapeType type, Color color, FillBehavior fb) {
        if(fb.getClass().equals(NoFill.class)){
            return new MyShape(type.createShape(),color, new NoFill());
        } else {
            return new MyShape(type.createShape(),color, new Fill());
        }
    }
}
