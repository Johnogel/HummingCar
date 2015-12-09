/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.tasks;

import robot.interfaces.Observer;
import robot.interfaces.Subject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johnogel
 */
public class TickerTask implements Runnable, Subject{
private int time, delay;
private ArrayList<Observer> observers;
    public TickerTask(int delay){
        time = 0;
        this.delay = delay;
        observers = new ArrayList();
    }
    
    @Override
    public void run() {
        while (true){
            time++;
            if(time % 30 == 0){
                this.notifyObservers(this);
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(TickerTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void registerObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers(Object o) {
        for (Observer obs : observers){
            obs.update(o);
        }
    }
    
}
