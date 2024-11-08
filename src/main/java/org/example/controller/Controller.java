package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.model.shape.factory.ShapeType;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.NoFill;
import org.example.model.shape.observer.EventListeners;
import org.example.model.shape.observer.EventManager;
import org.example.model.shape.observer.LoggingAlertListener;
import org.example.model.shape.observer.ShapeAlertListener;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

// TODO: 24.10.2024 Сделать singleton класс
public class Controller {
    private static Controller instance;
    private Model model;
    private MyFrame frame;
    private MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;

    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }

        return instance;
    }

    private Controller() {
        EventListeners shapeAlertListener = new ShapeAlertListener();
        EventListeners loggingAlertListener = new LoggingAlertListener();
        EventManager eventManager = new EventManager();

        model = new Model(eventManager);

        //MyShape shape = new MyShape(new Rectangle2D.Double());
//        MyShape shape = new MyShape(factory.createShape(ShapeType.ELIPSE));
        MyShapeFactory factory = new MyShapeFactory();
//        MyShape shape = factory.createShape(ShapeType.ELIPSE, Color.CYAN, new Fill());
        MyShape shape = factory.createShape(ShapeType.ELIPSE, null, null);

        model.setMyShape(shape);

        panel = new MyPanel(this);

        frame = new MyFrame();
        frame.setPanel(panel);
        // TODO: 25.10.2024 Поменять наблюдатель на более современную реализацию
        // Заменить???
        model.addObserver(panel);

        eventManager.subscribe(shapeAlertListener);
        eventManager.subscribe(loggingAlertListener);
        //eventManager.subscribe(System.out::println);
    }
    public void getPointOne(Point2D p){
        firstPoint = p;
    }
    public void getPointTwo(Point2D p){
        secondPoint = p;
        model.changeShape(firstPoint, secondPoint);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
