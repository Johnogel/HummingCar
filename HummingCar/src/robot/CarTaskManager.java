/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author Johnogel
 */
public class CarTaskManager {
private Controller car;
private ExecutorService executor;
private ArrayList<Thread> threads;
private AutoControl auto;
public static int LED = 0;
    public CarTaskManager(Controller car){
        this.car = car;
        threads = new ArrayList();
        
    }
    
    public void initialize() throws InterruptedException{
        
        
        
        Thread leds = new Thread(new LEDTask(car, 1, 30));
        
        threads.add(leds);
        
        leds.start();
        
        auto = new AutoControl(car);
        
        //auto.start();
        
    }
    
    public Controller getCar(){
        return car;
    }
    
    public void startAuto() throws InterruptedException{
        auto.start();
    }
    
    public void toggleAuto() throws InterruptedException{
        auto.toggleAuto();
    }
    
    public void updateAuto() throws InterruptedException{
        auto.updateAuto();
    }
    
    public void stop(int task){
        
        threads.get(task).interrupt();
        
    }
    
}
