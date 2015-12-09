package robot.controllers;


import robot.support.WrapperValue;
import robot.interfaces.Observer;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnogel
 */
public class AutoControl implements Observer{
private Controller car;
private WrapperValue run_time; 
private int max_speed = 255;
private final double DELTA_SWEEP = 1.3;
private boolean mask[] = {true, true, true, true};
private boolean auto;

private int values_1[] = {255, 0, 255, 0};

private int values_2[] = {0, 255, 0, 255};

    public AutoControl(Controller car){
        
        this.car = car;
        run_time = new WrapperValue();
        run_time.setIntValue(0);
       
    }
    
    //run robot automatically
    public void start() throws InterruptedException{
        
        
        car.setSpeed(max_speed);
        Random gen = new Random();

        auto = true;
        while (auto){
            
            
            for(int i = 1; i <=4; i++){

                //car.getRobot().setLED(i, gen.nextInt(255));
            }

            Thread.sleep(60l);
            resolve();

            run_time.increment();
            
            System.out.println("Main Time: " + run_time.getIntValue());
            
        }
   
        
    }
    
    public void updateAuto() throws InterruptedException{
    
        if (auto){


            for(int i = 1; i <=4; i++){

                //car.getRobot().setLED(i, gen.nextInt(255));
            }

            Thread.sleep(60l);
            resolve();

            run_time.increment();

            System.out.println("Main Time: " + run_time.getIntValue());

        }
    }
    
    private void resolveTurnRight(long sweep) throws InterruptedException{
        while(car.frontCollision())
        {

            turnRight(sweep);
            sweep += DELTA_SWEEP;
            //car.getRobot().setLEDs(mask, values_1);
            
            
            if(car.frontCollision()){
                turnLeft(sweep);
                sweep += DELTA_SWEEP;
                //car.getRobot().setLEDs(mask, values_2);
            }
        }
    
    }
    
    public void toggleAuto(){ 
        auto = !auto;
    }
    //turns right until path is available
    public void resolve() throws InterruptedException{
        
        long sweep = 2;
 
        if (Math.random() < .50 ){
            resolveTurnRight(sweep);
        }
        else{
            resolveTurnLeft(sweep);
        }
        
        car.setSpeed(max_speed);
        

        //Thread.sleep(598l);
        
     
        
    }
    
    //turns left until path is available
    private void resolveTurnLeft(long sweep) throws InterruptedException{
        while(car.frontCollision())
        {

            turnLeft(sweep);
            sweep += DELTA_SWEEP;
            //car.getRobot().setLEDs(mask, values_1);
            
            
            if(car.frontCollision()){
                turnRight(sweep);
                sweep += DELTA_SWEEP;
                //car.getRobot().setLEDs(mask, values_2);
            }
        }
        
        
    }
    
    
    public void turnRight() throws InterruptedException{
        
        
        car.turnRight();
        
        long delay = Math.abs(car.getSpeed()*3);
        
        Thread.sleep(delay);
        //stops turning
        
       
        
        
    }
    
    public void turnLeft() throws InterruptedException{

        car.turnLeft();
        
        long delay = Math.abs(car.getSpeed()*3);
        
        Thread.sleep(delay);
     
    }

   
    

    private void turnRight(long sweep) throws InterruptedException{
        
        
        car.turnRight();
        
        long delay = Math.abs(car.getSpeed()*sweep);
        
        Thread.sleep(delay);
        //stops turning
        
       
        
        
    }
    
    private void turnLeft(long sweep) throws InterruptedException{
        
        
            
        car.turnLeft();
        
        long delay = Math.abs(car.getSpeed()*sweep);
        
        Thread.sleep(delay);
     
    }

    @Override
    public void update(Object o) {
        
    }

   
    
}
