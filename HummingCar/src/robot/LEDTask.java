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
public class LEDTask implements Observer, Runnable{
private Controller car;
private int id;
    public LEDTask(Controller car, int id){
        this.car = car;
        this.id = id;
        car.setLED(id, 200);
    }

    @Override
    public void update(Object o) {
        WrapperValue value = (WrapperValue) o;
        int final_value = 255 - value.getIntValue();
        if(value.getBooleanValue()){
            car.setLED(id, 255);
        }
        else{
            car.setLED(id, 50);
        }
        
    }

    @Override
    public void run() {
        
    }
    
}
