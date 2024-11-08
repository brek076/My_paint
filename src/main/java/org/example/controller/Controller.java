package org.example.controller;

import org.example.controller.action.ActionDraw;
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
    private ActionDraw actionDraw;
    public static ShapeType selectedShape = ShapeType.RENCTAGLE;
    private MyShapeFactory factory;

    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }

        return instance;
    }

    private Controller() {
        panel = new MyPanel(this);
        frame = new MyFrame();
        frame.setPanel(panel);

        MenuController menuController = new MenuController();
        frame.setJMenuBar(menuController.getMenuBar());

        EventListeners shapeAlertListener = new ShapeAlertListener();
        EventListeners loggingAlertListener = new LoggingAlertListener();
        EventManager eventManager = new EventManager();

        model = Model.getInstance(eventManager);

        factory = new MyShapeFactory();

        // TODO: 25.10.2024 Поменять наблюдатель на более современную реализацию
        // Заменить???
        model.addObserver(panel);

        eventManager.subscribe(shapeAlertListener);
        eventManager.subscribe(loggingAlertListener);

        this.actionDraw = new ActionDraw(model, factory.createShape(selectedShape, null, null));
    }
    public void mousePressed(Point p){
        this.actionDraw = new ActionDraw(model, factory.createShape(selectedShape, null, null));
        this.actionDraw.createShape(p);
    }

    public void mouseDragged(Point p){
        this.actionDraw.stretchShape(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);

    }
}
