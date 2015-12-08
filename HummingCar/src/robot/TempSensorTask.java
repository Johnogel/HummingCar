/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

/**
 *
 * @author Johnogel
 */
public class TempSensorTask implements Observer, Subject{
private Controller car;
private int delay;
    public TempSensorTask(Controller car){
        this.car = car;
        this.delay = delay;
    }
    
    @Override
    public void update(Object o) {
    }

    @Override
    public void registerObserver(Observer obs) {
    }

    @Override
    public void removeObserver(Observer obs) {
    }

    @Override
    public void notifyObservers(Object o) {
    }
    
}
