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
public class CameraManager implements Observer, Subject{
private ShellCommandManager sh;
private AutoControl auto;

    public CameraManager(AutoControl auto){
        sh = new ShellCommandManager();
        this.auto = auto;
    }
    
    @Override
    public void update(Object o) {
        auto.toggleAuto();
        System.out.println(sh.executeCommand("raspistill -o cur_pos.jpg"));
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
