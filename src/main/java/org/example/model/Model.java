package org.example.model;

import org.example.controller.Controller;
import org.example.model.shape.observer.EventManager;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;

// TODO: 25.10.2024 Сделать singleton класс
public class Model extends Observable{
    private static Model instance;
    private MyShape currentShape;
    private EventManager eventManager;
    public static ArrayList<MyShape> myShapes = new ArrayList<MyShape>();

    public Model(EventManager  eventManager) {
        this.eventManager = eventManager;
    }

    public static Model getInstance(EventManager  eventManager){
        if(instance == null){
            instance = new Model(eventManager);
        }

        return instance;
    }

    public void setMyShape(MyShape myShape) {
        this.currentShape = myShape.clone();
        eventManager.notifyAll("setMyShape");
    }

    public void changeShape(Point2D x, Point2D y) {
        currentShape.setFrame(x, y);
        this.setChanged();
        this.notifyObservers();
        eventManager.notifyAll("changeShape");
    }

    public void draw(Graphics2D g) {
        currentShape.draw(g);
        eventManager.notifyAll("draw");
    }

    public void createCurrentShape(MyShape shape){
        this.currentShape = shape;
        //Метод должен устанавливать
        //currentShape в переданную фигуру????
        System.out.println(shape);

        myShapes.add(shape);
    }
}
