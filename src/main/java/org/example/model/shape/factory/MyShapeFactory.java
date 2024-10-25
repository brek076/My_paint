package org.example.model.shape.factory;

import java.awt.geom.RectangularShape;

public class MyShapeFactory {

        public RectangularShape createShape(ShapeType type){
            return type.createShape();
    }
}
