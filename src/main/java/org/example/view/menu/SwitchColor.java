package org.example.view.menu;

import org.example.controller.MenuState;

import javax.swing.*;
import java.awt.*;

public class SwitchColor implements AppCommand{
    public SwitchColor(MenuState menuState,  boolean useDefault,  Color defaultColor) { // , JRadioButtonMenuItem radioButton
        this.radioButton = radioButton;
        this.useDefault = useDefault;
        this.menuState = menuState;
        this.defaultColor = defaultColor;
    }

    private JRadioButtonMenuItem radioButton;
    private boolean useDefault;
    private MenuState menuState;
    private Color defaultColor;
    @Override
    public void execute() {

    }
}
