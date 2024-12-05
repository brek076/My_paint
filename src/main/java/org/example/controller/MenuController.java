package org.example.controller;

import org.example.model.Model;
import org.example.model.shape.factory.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuController {
    private static MenuController instance;
    private MenuState menuState;

    public MenuState getMenuState() {
        return menuState;
    }

    public JMenuBar getMenuBar() {
        return menu;
    }

    private JMenuBar menu;

    private MenuController(MenuState menuState) {
        this.menuState = menuState;

        menu = new JMenuBar();
        JMenu shapeMenu = createShapeMenu();
        JMenu fllMenu = createFillMenu();
        JMenu colorMenu = createColorMenu();
        JMenu saveOpenMenu = createSaveOpenMenu();
        menu.add(saveOpenMenu);
        menu.add(shapeMenu);
        menu.add(fllMenu);
        menu.add(colorMenu);
    }

    public static MenuController getInstance(MenuState menuState){
        if(instance == null){
            instance = new MenuController(menuState);
        }

        return instance;
    }


    private JMenu createShapeMenu() {
        // Фигуры
        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(e -> menuState.setShapeType(ShapeType.RECTANGLE));
        square.setSelected(true);
        shapeMenu.add(square);

        group.add(square);
        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(e -> menuState.setShapeType(ShapeType.ELLIPSE));
        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;
    }

    private JMenu createFillMenu(){
        // Заливка
        JMenu fillMenu = new JMenu("Заливка");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem noFilled = new JRadioButtonMenuItem("Без Заливки");
        noFilled.addActionListener(e -> menuState.setFill(false));
        noFilled.setSelected(true);
        group.add(noFilled);
        fillMenu.add(noFilled);

        JRadioButtonMenuItem filled = new JRadioButtonMenuItem("С Заливкой");
        filled.addActionListener(e -> menuState.setFill(true));
        group.add(filled);
        fillMenu.add(filled);

        return fillMenu;
    }


    private JMenu createColorMenu(){
        // Цвет
        JMenu colorMenu = new JMenu("Цвета");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem red = new JRadioButtonMenuItem("Красный");
        red.addActionListener(e -> menuState.setColor(Color.RED));
        red.setSelected(true);
        group.add(red);
        colorMenu.add(red);

        JRadioButtonMenuItem green = new JRadioButtonMenuItem("Зеленый");
        green.addActionListener(e -> menuState.setColor(Color.GREEN));
        group.add(green);
        colorMenu.add(green);

        JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Синий");
        blue.addActionListener(e -> menuState.setColor(Color.BLUE));
        group.add(blue);
        colorMenu.add(blue);

        return colorMenu;
    }

    private JMenu createSaveOpenMenu(){
        // Открыть/сохранить файл
        JMenu saveOpenMenu = new JMenu("Файл");
        ButtonGroup group = new ButtonGroup();

        JMenuItem openItem = new JMenuItem(new AbstractAction("Открыть") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Model.setMyShapes(FileController.open());
            }
        });
        group.add(openItem);
        saveOpenMenu.add(openItem);

        JMenuItem saveItem = new JMenuItem(new AbstractAction("Сохранить") {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileController.save(Controller.getInstance().getModel());
            }
        });
        group.add(saveItem);
        saveOpenMenu.add(saveItem);

        return saveOpenMenu;
    }

}
