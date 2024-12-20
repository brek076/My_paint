package org.example.model.shape.fill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.RectangularShape;

public class NoFill implements FillBehavior {
    private Color color;
    private RectangularShape shape;

    public NoFill() {
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics2D g) {
        Paint paint = g.getPaint();
        g.setPaint(color);
        g.draw(shape);
        g.setPaint(paint);
    }

    @Override
    public void setShape(RectangularShape s) {
        shape = s;
    }

    @Override
    public RectangularShape getShape() {
        return shape;
    }
}
