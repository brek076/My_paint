package org.example.controller;

import org.example.model.shape.factory.ShapeType;

import java.awt.*;

public class MenuState {
    private boolean fill;
    private Color  color;
    private ShapeType  shapeType;

    public MenuState() {
        this.fill = false;
        this.color = Color.RED;
        this.shapeType = ShapeType.RECTANGLE;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }


    public boolean isFill() {
        return fill;
    }

    public Color getColor() {
        return color;
    }

    public ShapeType getShapeType() {
        return shapeType;
    }
}
