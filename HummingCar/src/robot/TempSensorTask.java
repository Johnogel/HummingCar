/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.util.ArrayList;

/**
 *
 * @author Johnogel
 */
public class TempSensorTask implements Observer, Subject{
private ArrayList<Observer> observers;
private Controller car;
private int delay;
    public TempSensorTask(Controller car){
        this.car = car;
        observers = new ArrayList();
    }
    
    @Override
    public void update(Object o) {
        
        
        notifyObservers(o);
    }

    @Override
    public void registerObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove((Object) obs);
    }

    @Override
    public void notifyObservers(Object o) {
        for (Observer obs : observers){
            obs.update(o);
        }
    }
    
}
