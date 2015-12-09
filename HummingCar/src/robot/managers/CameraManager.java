/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.managers;

import robot.support.ShellCommandManager;
import robot.interfaces.Observer;
import robot.interfaces.Subject;
import java.util.ArrayList;
import robot.controllers.AutoControl;

/**
 *
 * @author Johnogel
 */
public class CameraManager implements Observer, Subject{
private ShellCommandManager sh;
private AutoControl auto;
private ArrayList<Observer> observers;

    public CameraManager(AutoControl auto){
        sh = new ShellCommandManager();
        this.auto = auto;
        observers = new ArrayList();
    }
    
    @Override
    public void update(Object o) {
        //auto.toggleAuto();
        System.out.println(sh.executeCommand("raspistill -o cur_pos.jpg"));
        //auto.toggleAuto();
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
        for (Observer obj : observers){
            obj.update(o);
        }
    }
    
}
