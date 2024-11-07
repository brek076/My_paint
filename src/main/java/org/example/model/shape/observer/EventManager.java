package org.example.model.shape.observer;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<EventListeners> listeners;

    public EventManager() {
        listeners = new ArrayList<>();
    }

    public void subscribe(EventListeners eventListeners) {
        listeners.add(eventListeners);
    }

    public void unsubscribe(EventListeners eventListeners) {
        listeners.remove(eventListeners);
    }

    public void notifyAll(String event) {
        listeners.forEach(eventListeners -> eventListeners.update(event));
    }
}
