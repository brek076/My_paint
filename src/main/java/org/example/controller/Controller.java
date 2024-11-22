package org.example.controller;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.controller.action.AppAction;
import org.example.model.Model;
import org.example.model.shape.factory.ShapeCreator;
import org.example.model.shape.observer.EventListeners;
import org.example.model.shape.observer.EventManager;
import org.example.model.shape.observer.LoggingAlertListener;
import org.example.model.shape.observer.ShapeAlertListener;
import org.example.view.MyFrame;
import org.example.view.MyPanel;
import org.example.view.menu.MenuCreator;

import java.awt.*;

// TODO: 24.10.2024 Сделать singleton класс
public class Controller {
    private static Controller instance;
    private Model model;
    private MyFrame frame;
    private MyPanel panel;
    private AppAction appAction;
    private ShapeCreator factory;
    public MenuState menuState;

    private MenuCreator menuCreator;
//    public static ShapeType selectedShape = ShapeType.RENCTAGLE;
//    public static boolean selectedFill = false;
//    public static Color selectedColor = Color.RED;

    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }

        return instance;
    }

    private Controller() {
        menuState = new MenuState();
        factory = new ShapeCreator();
        panel = new MyPanel(this);
        frame = new MyFrame();
        frame.setPanel(panel);

        MenuCreator menuCreator = MenuCreator.getInstance(menuState);
        menuCreator.setState(menuState);
        menuCreator.setModel(model);
        frame.setJMenuBar(menuCreator.getMenuBar());

        frame.add(menuCreator.createToolBar(), BorderLayout.NORTH);
//        menuController = MenuCreator.getInstance(menuState);
//        frame.setJMenuBar(menuController.getMenuBar());


        // TODO: 25.10.2024 Поменять наблюдатель на более современную реализацию
        // Заменить???
        EventListeners shapeAlertListener = new ShapeAlertListener();
        EventListeners loggingAlertListener = new LoggingAlertListener();
        EventManager eventManager = new EventManager();


        eventManager.subscribe(shapeAlertListener);
        eventManager.subscribe(loggingAlertListener);

        model = Model.getInstance(eventManager);
        model.addObserver(panel);
    }
    public void mousePressed(Point p){
        this.factory.configurate(menuState);
        this.appAction = new ActionMove();
        appAction.mousePressed(p);
        if(!appAction.isFind()) {
            this.appAction = new ActionDraw(model, factory.createShape());
            this.appAction.mousePressed(p);
        }
    }

    public void mouseDragged(Point p){
        this.appAction.mouseDragged(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
