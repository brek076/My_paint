package org.example.controller;

import org.example.model.Model;
import org.example.model.shape.factory.ShapeType;
import org.example.model.shape.observer.EventManager;

import javax.swing.*;

public class MenuController {
    private static MenuController instance;

    public JMenuBar getMenuBar() {
        return menu;
    }

    private JMenuBar menu;

    public MenuController() {
        menu = new JMenuBar();
        JMenu colorMenu = createShapeMenu();
        menu.add(colorMenu);
    }

    public static MenuController getInstance(){
        if(instance == null){
            instance = new MenuController();
        }

        return instance;
    }


    private JMenu createShapeMenu() {
        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(e -> Controller.selectedShape = ShapeType.RENCTAGLE);
        square.setSelected(true);
        shapeMenu.add(square);
        group.add(square);
        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> Controller.selectedShape = ShapeType.ELLIPSE);
        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;
    }
}
