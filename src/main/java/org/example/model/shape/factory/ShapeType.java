package org.example.model.shape.factory;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public enum ShapeType {
    RECTANGLE {
        @Override
        public Rectangle2D createShape(){
            return new Rectangle2D.Double();
        }
    },
    ELLIPSE{
        @Override
        public Ellipse2D createShape(){
            return new Ellipse2D.Double();
        }
    };
    public  abstract RectangularShape createShape();
}