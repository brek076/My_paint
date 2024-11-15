package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionMove implements  AppAction{

    private MyShape sampleShape;
    private MyShape shape;
    private Model model;
    private Point2D[] points = new Point2D[2];

    @Override
    public void mousePressed(Point point) {
        shape = model.getMyShapes()
                .stream()
                .filter(myShape -> myShape.getFb().getShape().contains(point))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void mouseDragged(Point point) {
        if(shape == null)return;
        double deltaX = shape.getFb().getShape().getMaxX() - shape.getFb().getShape().getMinX();
        double deltaY = shape.getFb().getShape().getMaxY()  - shape.getFb().getShape().getMinY();

        // Диагональ
    }
}
