package org.example.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

@JsonAutoDetect
public class MyShape implements Cloneable{
    private FillBehavior fb;

    public FillBehavior getFb() {
        return fb;
    }

    // TODO: 25.10.2024  Попробовать вызовы через разные конструкторы, затем переделать создание через фабрику
    public MyShape() {}
    @JsonIgnore
    public void setFb(FillBehavior fb) {
        this.fb = fb;
        System.out.println(fb);
    }
    @JsonIgnore
    public void setFrame(Point2D x, Point2D y) {
        fb.getShape().setFrameFromDiagonal(x, y);
        // fb -> shape
    }
    @JsonIgnore
    public void draw(Graphics2D g) {
        System.out.println(fb);
        fb.draw(g);
    }

    public Color getColor(){
        return fb.getColor();
    }

    @JsonIgnore
    @Override
    public MyShape clone() {
        // Удалить по 11 заданию
        try {
            MyShape clone = (MyShape) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
