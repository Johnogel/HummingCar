/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Johnogel
 */
public class CarTaskManager {
private Controller car;
private ExecutorService executor;
    public CarTaskManager(Controller car){
        this.car = car;
        
        
    }
    
    public void initialize() throws InterruptedException{
        
        Thread leds = new Thread(new LEDTask(car, 1, 30));
        
        leds.start();
        
        AutoControl auto = new AutoControl(car);
        
        auto.start();
        
    }
    
}
