package org.example.view.menu;

import org.example.controller.MenuState;
import org.example.model.Model;
import org.example.model.shape.factory.ShapeType;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class MenuCreator {
    private static MenuCreator instance;
    private MenuState menuState;
    private JMenuBar menu;
    private JToolBar toolBar;
    private Model model;

    private MenuCreator(MenuState menuState) {
        this.menuState = menuState;

        menu = new JMenuBar();
        JMenu shapeMenu = createShapeMenu();
        JMenu fllMenu = createFillMenu();
        JMenu colorMenu = createColorMenu();
        menu.add(shapeMenu);
        menu.add(fllMenu);
        menu.add(colorMenu);
    }

    public static MenuCreator getInstance(MenuState menuState){
        if(instance == null){
            instance = new MenuCreator(menuState);
        }

        return instance;
    }

    public void setState(MenuState state){
        this.menuState = state;
    }

    public void setModel(Model model){
        this.model = model;
    }

    public JToolBar createToolBar(){
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar();
        //Вариант со stream API Java 1.8+
//        subMenuItems.forEach(jToolBar::add);
        ///////////////////////////////////////
        //Вариант без использования stream API
        for (Action x : subMenuItems) {
            jToolBar.add(x);
        }
        return jToolBar;
    }

    private ArrayList<Action> createToolBarItems(){
        ArrayList<Action> menuItems = new ArrayList<>();

        //Метод createToolBarItems должен создавать набор toolbar меню (Включая
        //иконки)
        URL colorUrl = getClass().getClassLoader().getResource("ico/color.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        AppCommand colorCommand = new SwitchColor(menuState, false, null);
        // Для смены состояния + JradioButton - rgbButton
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));

        return menuItems;
    }

    private JMenu createShapeMenu() {
        // Фигуры
        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(e -> menuState.setShapeType(ShapeType.RENCTAGLE));
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

    public MenuState getMenuState() {
        return menuState;
    }

    public JMenuBar getMenuBar() {
        return menu;
    }
}
