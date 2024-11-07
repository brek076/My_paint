package org.example.model.shape.observer;

public class LoggingAlertListener implements EventListeners {
    @Override
    public void update(String event) {
        System.out.println("Write to log: " + event);
    }
}
