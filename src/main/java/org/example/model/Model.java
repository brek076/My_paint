package org.example.model;

import org.example.controller.Controller;
import org.example.model.shape.observer.EventManager;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;

// TODO: 25.10.2024 Сделать singleton класс
public class Model extends Observable{
    private static Model instance;
    private MyShape currentShape; // Убрать
    private EventManager eventManager;

    public static ArrayList<MyShape> getMyShapes() {
        return myShapes;
    }

    public static ArrayList<MyShape> myShapes = new ArrayList<MyShape>();

    private Model(EventManager  eventManager) {
        this.eventManager = eventManager;
    }

    public static Model getInstance(EventManager  eventManager){
        if(instance == null){
            instance = new Model(eventManager);
        }

        return instance;
    }

//    public void setMyShape(MyShape myShape) {
//        this.currentShape = myShape.clone();
//        eventManager.notifyAll("setMyShape");
//    }
//
//    public void changeShape(Point2D x, Point2D y) {
//        currentShape.setFrame(x, y);
//        this.setChanged();
//        this.notifyObservers();
//        eventManager.notifyAll("changeShape");
//    }

    public void draw(Graphics2D g) {
        currentShape.draw(g);
        eventManager.notifyAll("draw");
//        eventManager.notifyAll("setMyShape");
    }

    public void removeLastShape(){
        myShapes.remove(myShapes.size() - 1 );
    }

    public MyShape getLastShape(){
        return myShapes.get(myShapes.size() - 1 );
    }

    public void createCurrentShape(MyShape shape){
        //this.currentShape = shape;
//        eventManager.notifyAll("changeShape");
        myShapes.add(shape);
    }
}
