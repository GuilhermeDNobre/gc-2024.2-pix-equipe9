package com.ufc.pix.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class EmailSubject {

    private final List<EmailObserver> observers = new ArrayList<>();

    protected void addObserver(EmailObserver observer){
        this.observers.add(observer);
    };

    protected void notifyObservers(String to, String subject, String message){
        for (var o : observers) {
            o.update(to, subject, message);
        }
    };

}
