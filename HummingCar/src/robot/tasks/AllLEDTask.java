/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.tasks;

import robot.interfaces.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import robot.Controller;

/**
 *
 * @author Johnogel
 */
public class AllLEDTask implements Runnable, Observer{
private Controller car;
private long delay;
public static final int 
        LED_1 = 1,
        LED_2 = 2,
        LED_3 = 3,
        LED_4 = 4;

private int LED_state;
    public AllLEDTask(Controller car, int LED, long delay){
        this.delay = delay;
        
        LED_state = LED;
        this.car = car;
        
    }
    
    @Override
    public void run(){
        
        while(true){
            
            updateLEDs();
            
        }
        
        
            
        
        
    }
    
    public void stop(){
        boolean mask[] = {true, true, true, true};
        int intensities[] = {0,0,0,0};
        car.setLEDs(mask, intensities);
        
    }
    
    public void updateLEDs(){
        
        switch (car.getState()){
            case Controller.MOVING_FORWARD:
                forwardLEDs();
                break;
                
            case Controller.MOVING_BACKWARD:
                backwardLEDs();
                break;
                
            case Controller.TURNING_LEFT:
                turningLeftLEDs();
                break;
                
            case Controller.TURNING_RIGHT:
                turningRightLEDs();
                break;
                
                
                
                
        }
    }
    
    public void forwardLEDs(){
        
        try {
   
                car.setLED(LED_state, 200);
                for(int i = 1; i <= 4; i++){
                    if(i != LED_state){
                        car.setLED(i, 0);
                    }
                }
                
                LED_state += 1;
                if(LED_state > 4){
                    LED_state = 1;
                }
                
                Thread.sleep(delay);
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(AllLEDTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public void backwardLEDs(){
        try {
                
                
                car.setLED(LED_state, 200);
                for(int i = 1; i <= 4; i++){
                    if(i != LED_state){
                        car.setLED(i, 0);
                    }
                }
                
                LED_state -= 1;
                if(LED_state < 1){
                    LED_state = 4;
                }
                Thread.sleep(delay);
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(AllLEDTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public void stoppedLEDs(){
        
    }
    
    public void turningRightLEDs(){
        try {
                
               
                
                //car.setLED(LED_state, 200);
                for(int i = 1; i <= 4; i++){
                    if(i == LED_state || ((i == LED_state + 2)||(i == LED_state - 2))){
                        System.out.println("LED -- RIGHT");
                        car.setLED(i, 200);
                        if (i + 2 <= 4){
                            car.setLED(i+2, 200);
                        }
                        else{
                            car.setLED(i-2, 200);
                        }
                    }
                    else{
                        car.setLED(i, 0);
                    }
                }
                
                LED_state += 1;
                if(LED_state > 4){
                    LED_state = 1;
                }
                
                Thread.sleep(delay);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(AllLEDTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    
    public void turningLeftLEDs(){
        
        try {
                
                
                
                //car.setLED(LED_state, 200);
                for(int i = 1; i <= 4; i++){
                    if(i == LED_state || ((i == LED_state + 2)||(i == LED_state - 2))){
                        System.out.println("LED -- LEFT");
                        car.setLED(i, 200);
                        if (i + 2 <= 4){
                            car.setLED(i+2, 200);
                        }
                        else{
                            car.setLED(i-2, 200);
                        }
                    }
                    else{
                        car.setLED(i, 0);
                    }
                }
                
                
                LED_state += 1;
                if(LED_state > 4){
                    LED_state = 1;
                }
                
                Thread.sleep(delay);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(AllLEDTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @Override
    public void update(Object o) {
    }
    
}
