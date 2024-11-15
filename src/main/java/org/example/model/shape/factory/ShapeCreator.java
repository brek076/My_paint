package org.example.model.shape.factory;

import org.example.controller.Controller;
import org.example.controller.MenuState;
import org.example.model.MyShape;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;
import org.example.model.shape.fill.NoFill;

import java.awt.geom.RectangularShape;

public class ShapeCreator {
    private static ShapeCreator instance;
    private MenuState state;

    private static ShapeCreator getInstance(){
        if(instance == null){
            instance = new ShapeCreator();
        }

        return instance;
    }

    public void configurate(MenuState state){
        this.state = state;
    }

    public MyShape createShape(){
        MyShape myShape = new MyShape();
        RectangularShape shape  = state.getShapeType().createShape();

        FillBehavior fb = state.isFill() ? new Fill() : new NoFill();
        fb.setColor(state.getColor());
        fb.setShape(shape);

        myShape.setFb(fb);
        return myShape;
    }

}
