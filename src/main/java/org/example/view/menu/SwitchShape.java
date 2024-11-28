package org.example.view.menu;

import org.example.controller.MenuState;
import org.example.model.shape.factory.ShapeType;

import javax.swing.*;
import java.awt.*;

public class SwitchShape implements AppCommand{
    private ShapeType shapeType;
    private MenuState state;

    public SwitchShape(ShapeType shapeType, MenuState state) {
        this.shapeType = shapeType;
        this.state = state;
    }

    @Override
    public void execute() {
        state.setShapeType(this.shapeType);
    }
}
