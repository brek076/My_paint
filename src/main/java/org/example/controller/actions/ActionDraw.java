package org.example.controller.actions;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction{
    private MyShape sampleShape;
    private MyShape shape;
    private Model model;
    private Point2D[] points = new Point2D[2];
    private  MyShape drawableShape;

    public ActionDraw(Model model, MyShape shape) {
        this.model = model;
        this.sampleShape = shape;
    }

//    public void stretchShape(Point point){
//        this.points[0] = (Point2D) point;
//        this.shape.setFrame(points[0], points[1]);
//    }
//
//    public void createShape(Point point){
//        points[1] = (Point2D) point;
//        System.out.println(sampleShape);
//        this.shape = sampleShape.clone();
//        model.createCurrentShape(shape);
//        // Сделать создание с помощью факторки
//    }

    @Override
    public void mousePressed(Point point) {
        points[1] = (Point2D) point;
        System.out.println(sampleShape);
        this.shape = sampleShape.clone();
        drawableShape = sampleShape;
        model.createCurrentShape(shape);
        // Сделать создание с помощью факторки
    }

    @Override
    public void mouseDragged(Point point) {
        this.points[0] = (Point2D) point;
        this.shape.setFrame(points[0], points[1]);
        drawableShape.setFrame(points[0], points[1]);

    }

    @Override
    public boolean isFind() {
        return false;
    }

    @Override
    public void execute() {
        model.createCurrentShape(drawableShape);
        //model.update();
    }

    @Override
    public void unexecute() {
        drawableShape = model.getLastShape();
        model.removeLastShape();
        //model.update();
    }

    @Override
    public AppAction cloneAction() {
        ActionDraw actionDraw = new ActionDraw(model, this.drawableShape);
        actionDraw.sampleShape = sampleShape.clone();
        actionDraw.drawableShape = drawableShape;
        return actionDraw;
    }
}
