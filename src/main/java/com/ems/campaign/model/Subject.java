package com.ems.campaign.model;

import java.util.*;

public abstract class Subject {
    private List<IObserver> observers;
    private Map<String, Object> map;

    public Subject() {
        observers = new ArrayList<>();
        map = new HashMap<>();
    }

    public void attach(IObserver observer) {
        observers.add(observer);
    }

    public void detach(IObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        ListIterator<IObserver> iterator = observers.listIterator();
        while (iterator.hasNext()) {
            iterator.next().update(this);
        }
    }

    public void setValue(String key, Object value) {
        map.put(key, value);
    }

    public Object getValue(String key) {
        return map.get(key);
    }
}
