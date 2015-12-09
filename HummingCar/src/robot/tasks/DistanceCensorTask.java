/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.tasks;

import robot.interfaces.Observer;
import robot.interfaces.Subject;
import java.util.ArrayList;
import robot.Controller;

/**
 *
 * @author Johnogel
 */
public class DistanceCensorTask implements Runnable, Subject{

private Controller car;
private ArrayList<Observer> observers;

    public DistanceCensorTask(Controller car, ArrayList<Observer> observers){
        this.car = car;
        this.observers = observers;
    }

    @Override
    public void run() {
        
        while(true){
            
        }
        
    }

    @Override
    public void registerObserver(Observer o) {
    }

    @Override
    public void removeObserver(Observer o) {
    }

    @Override
    public void notifyObservers(Object o) {
    }
    
}
