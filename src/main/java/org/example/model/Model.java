package org.example.model;

import org.example.model.shape.observer.EventManager;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.Observable;

// TODO: 25.10.2024 Сделать singleton класс
public class Model extends Observable{
    private MyShape currentShape;
    private final EventManager  eventManager;

    public Model(EventManager  eventManager) {
        this.eventManager = eventManager;
    }

    public void setMyShape(MyShape myShape) {
        this.currentShape = myShape;
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
}
