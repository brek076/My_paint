package org.example.model.shape.observer;

public class ShapeAlertListener implements EventListeners{

    @Override
    public void update(String event) {
        System.out.println("Shape update: " + event);
    }
}
