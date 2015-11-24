/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johnogel
 */
public class LightSensorTask implements Runnable, Subject{
private Controller car;
private int delay;
private boolean dark;
private ArrayList<Observer> observers;
    
    public LightSensorTask(Controller car, int delay){
        this.car = car;
        this.delay = delay;
        dark = false;
        observers = new ArrayList();
    }
    @Override
    public void run() {
        
        while (true){
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(LightSensorTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("LIGHT VALUE: "+car.getLightValue());
            if (car.getLightValue() < 80 && !dark){
                dark = true;
                WrapperValue wrap = new WrapperValue();
                wrap.setIntValue(car.getLightValue());
                wrap.setBooleanValue(dark);
                notifyObservers(wrap);
            }
            else if (car.getLightValue() > 80 && dark){
                dark = false;
                WrapperValue wrap = new WrapperValue();
                wrap.setIntValue(car.getLightValue());
                wrap.setBooleanValue(dark);
                notifyObservers(wrap);
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
