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
    private JRadioButtonMenuItem otherColor;

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

    public JToolBar createToolBar(){
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar();
        for (Action x : subMenuItems)
            jToolBar.add(x);
        return jToolBar;
    }

    private ArrayList<Action> createToolBarItems(){
        ArrayList<Action> menuItems = new ArrayList<>();


        // Для смены состояния в menu JradioButton, вроде и без этого можно - rgbButton
        URL colorUrl = getClass().getClassLoader().getResource("ico/color_16x16.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        AppCommand colorCommand = new SwitchColor(otherColor,false,  menuState, null);
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));

        // Эллипс
        URL ellipseUrl = getClass().getClassLoader().getResource("ico/ellipse_16x16.png");
        ImageIcon ellipseIco = ellipseUrl == null ? null : new ImageIcon(ellipseUrl);
        AppCommand ellipseCommand =  new SwitchShape(ShapeType.ELLIPSE, getMenuState());
        menuItems.add(new CommandActionListener("Цвет", ellipseIco, ellipseCommand));

        // Квадрат
        URL renctagleUrl = getClass().getClassLoader().getResource("ico/rectangular_16x16.png");
        ImageIcon rectangleIco = renctagleUrl == null ? null : new ImageIcon(renctagleUrl);
        AppCommand rectangleCommand =  new SwitchShape(ShapeType.RECTANGLE, getMenuState());
        menuItems.add(new CommandActionListener("Цвет", rectangleIco, rectangleCommand));

        // С заливкой
        URL fillUrl = getClass().getClassLoader().getResource("ico/fill_16x16.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        AppCommand fillCommand =  new SwitchFill(true, getMenuState());
        menuItems.add(new CommandActionListener("Цвет", fillIco, fillCommand));


        // Без заливки
        URL no_fillUrl = getClass().getClassLoader().getResource("ico/no_fill_16x16.png");
        ImageIcon no_fillIco = no_fillUrl == null ? null : new ImageIcon(no_fillUrl);
        AppCommand no_fillCommand =  new SwitchFill(false, getMenuState());
        menuItems.add(new CommandActionListener("Цвет", no_fillIco, no_fillCommand));

        return menuItems;
    }

    private JMenu createShapeMenu() {
        // Фигуры
        JMenu shapeMenu = new JMenu("Фигура");
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Прямоугольник");
        square.addActionListener(new CommandActionListener(new SwitchShape(ShapeType.RECTANGLE, getMenuState())));
        square.setSelected(true);
        shapeMenu.add(square);

        group.add(square);
        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener(new CommandActionListener(new SwitchShape(ShapeType.ELLIPSE, getMenuState())));
        shapeMenu.add(ellipse);
        group.add(ellipse);
        return shapeMenu;
    }

    private JMenu createFillMenu(){
        // Заливка
        JMenu fillMenu = new JMenu("Заливка");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem noFilled = new JRadioButtonMenuItem("Без Заливки");
        noFilled.addActionListener(new CommandActionListener(new SwitchFill(false, getMenuState())));
        noFilled.setSelected(true);
        group.add(noFilled);
        fillMenu.add(noFilled);

        JRadioButtonMenuItem filled = new JRadioButtonMenuItem("С Заливкой");
        filled.addActionListener(new CommandActionListener(new SwitchFill(true, getMenuState())));
        group.add(filled);
        fillMenu.add(filled);

        return fillMenu;
    }


    private JMenu createColorMenu(){
        // Цвет
        JMenu colorMenu = new JMenu("Цвета");
        ButtonGroup group = new ButtonGroup();

        JRadioButtonMenuItem red = new JRadioButtonMenuItem("Красный");
        red.addActionListener(new CommandActionListener(new SwitchColor(red, true, getMenuState(), Color.RED)));
        red.setSelected(true);
        group.add(red);
        colorMenu.add(red);

        JRadioButtonMenuItem other = new JRadioButtonMenuItem("Другой");
        otherColor = other;
        other.addActionListener(new CommandActionListener(new SwitchColor(red, false, getMenuState(), null)));
        group.add(other);
        colorMenu.add(other);
        // Опционально сделать изменение radiobutton в менюшке

//        JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Синий");
//        blue.addActionListener(e -> menuState.setColor(Color.BLUE));
//        group.add(blue);
//        colorMenu.add(blue);

        return colorMenu;
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public JMenuBar getMenuBar() {
        return menu;
    }

    public void setState(MenuState state){
        this.menuState = state;
    }

    public void setModel(Model model){
        this.model = model;
    }

}
