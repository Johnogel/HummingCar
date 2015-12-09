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
        
        auto = new AutoControl(car);
        
        Observer led_1 = new LEDTask(car, 1);
        Observer led_2 = new LEDTask(car, 2);
        Observer led_3 = new LEDTask(car, 3);
        Observer led_4 = new LEDTask(car, 4);
        Observer cam = new CameraManager(auto);
        

        Subject light_sensor = new LightSensorTask(car, 50);
        Subject temp_sensor = new TempSensorTask(car);
        Subject ticker = new TickerTask(1000);
        
        temp_sensor.registerObserver(cam);
        ticker.registerObserver((Observer)temp_sensor);
        
        
        Thread led_1_task = new Thread((Runnable)led_1);
        Thread led_2_task = new Thread((Runnable)led_2);
        Thread led_3_task = new Thread((Runnable)led_3);
        Thread led_4_task = new Thread((Runnable)led_4);
        
        Thread light_sensor_task = new Thread((Runnable) light_sensor);
        Thread ticker_task = new Thread((Runnable) ticker);
        
        threads.add(led_1_task);
        threads.add(led_2_task);
        threads.add(led_3_task);
        threads.add(led_4_task);
        threads.add(light_sensor_task);
        threads.add(ticker_task);
        
        for (Thread task : threads){
            task.start();
        }
        
        light_sensor.registerObserver(led_1);
        light_sensor.registerObserver(led_2);
        light_sensor.registerObserver(led_3);
        light_sensor.registerObserver(led_4);
       
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
