/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johnogel
 */
public class LEDTask extends Thread implements Runnable {
private Controller car;
private long delay;
public static final int 
        LED_1 = 1,
        LED_2 = 2,
        LED_3 = 3,
        LED_4 = 4;

private int LED_state;
    public LEDTask(Controller car, int LED, long delay){
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
                
                this.sleep(delay);
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(LEDTask.class.getName()).log(Level.SEVERE, null, ex);
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
                this.sleep(delay);
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(LEDTask.class.getName()).log(Level.SEVERE, null, ex);
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
                
                this.sleep(delay);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(LEDTask.class.getName()).log(Level.SEVERE, null, ex);
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
                
                this.sleep(delay);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(LEDTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
}
