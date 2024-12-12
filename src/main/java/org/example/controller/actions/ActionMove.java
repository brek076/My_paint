package org.example.controller.actions;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionMove implements  AppAction{

    private MyShape sampleShape;
    private MyShape shape;
    private Model model;
    private Point2D[] points = new Point2D[2];

    public boolean isFind(){
        return shape != null;
    }

    @Override
    public void execute() {

    }

    @Override
    public void unexecute() {

    }

    @Override
    public AppAction cloneAction() {
        return null;
    }

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
        if(shape == null) return;
        //два поинат ,которые слздаются почти тем же примером снизу. Диагональ (поинт 1, поинт 2)

        double deltaX = shape.getFb().getShape().getMaxX() - shape.getFb().getShape().getMinX();
        double deltaY = shape.getFb().getShape().getMaxY()  - shape.getFb().getShape().getMinY();


        Double newX = (point.x - deltaX);
        Double newY = (point.y - deltaY);
//
//
//        Point fp = new Point(newXFP.intValue(), newYFP.intValue());
//        Point sp = new Point(newXSP.intValue(), newYSP.intValue());

        // Диагональ
//        shape.getFb().getShape().setFrameFromDiagonal(sp, fp);
        shape.getFb().getShape().setFrame(newX, newY, shape.getFb().getShape().getWidth(),shape.getFb().getShape().getHeight());
    }
}
