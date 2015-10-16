package robot;


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
public class AutoControl{
private Controller car;
private intValue run_time; 
private int max_speed = 255;
private boolean mask[] = {true, true, true, true};

private int values_1[] = {255, 0, 255, 0};

private int values_2[] = {0, 255, 0, 255};

    public AutoControl(Controller car){
        
        this.car = car;
        run_time = new intValue(0);
       
    }
    
    //run robot automatically
    public void start() throws InterruptedException{
        
        
        car.setSpeed(max_speed);
        Random gen = new Random();

        boolean loop = true;
        while (loop){
            
            
            for(int i = 1; i <=4; i++){

                //car.getRobot().setLED(i, gen.nextInt(255));
            }

            Thread.sleep(60l);
            resolve();

            run_time.increment();
            
            System.out.println("Main Time: " + run_time.getValue());
            
        }
   
        
    }
    public void resolveTurnRight() throws InterruptedException{
        //boolean clear = false;
        
        turnRight();
        
//        while (!clear){
//            
//            if(!car.frontCollision()){
//                car.setSpeed(max_speed);
//                clear = true;
//            }
//            
//            else if(!car.backCollision()){
//                car.setSpeed(-max_speed);
//                clear = true;
//            }
//            
//            else{
//                turnRight();
//            }
//        }
    
    }
    
    //turns right until path is available
    public void resolve() throws InterruptedException{
        
        long sweep = 2;
        
        while(car.frontCollision())
        {

            turnRight(sweep);
            sweep += 1.5;
            //car.getRobot().setLEDs(mask, values_1);
            
            
            if(car.frontCollision()){
                turnLeft(sweep);
                sweep += 1.5;
                //car.getRobot().setLEDs(mask, values_2);
            }
        }
        
        car.setSpeed(max_speed);
        
        
       
        
      
        
            
            
            
        //Thread.sleep(598l);
        
     
        
    }
    
    //turns left until path is available
    public void resolveTurnLeft() throws InterruptedException{
        boolean clear = false;
        
        turnLeft();
        
        while (!clear){
            
            if(!car.frontCollision()){
                car.setSpeed(max_speed);
                clear = true;
            }
            
            else if(!car.backCollision()){
                car.setSpeed(-max_speed);
                clear = true;
            }
            
            else{
                turnLeft();
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

   
    

    public void turnRight(long sweep) throws InterruptedException{
        
        
        car.turnRight();
        
        long delay = Math.abs(car.getSpeed()*sweep);
        
        Thread.sleep(delay);
        //stops turning
        
       
        
        
    }
    
    public void turnLeft(long sweep) throws InterruptedException{
        
        
            
        car.turnLeft();
        
        long delay = Math.abs(car.getSpeed()*sweep);
        
        Thread.sleep(delay);
     
    }

   
    
}
