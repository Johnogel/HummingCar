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
public class LEDTask implements Runnable{
private Controller car;
    
    public LEDTask(Controller car){
        
        this.car = car;
        
    }
    
    @Override
    public void run(){
        
        
        
    }
    
    public void updateLEDs(){
        switch (car.getState()){
            case Controller.STOPPED:
                
                
        }
    }
    
    public void forwardLEDs(){
        
    }
}
