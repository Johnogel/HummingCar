package robot;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;

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
private int max_speed = 150;

    public AutoControl(Controller car){
        
        this.car = car;
        run_time = new intValue(0);
       
    }
    
    //run robot automatically
    public void start(){
        
        if (!car.frontCollision()){
            car.setSpeed(max_speed);
        }
        else if(!car.backCollision()){
            car.setSpeed(-max_speed);
        }
        else{
            resolveTurnRight();
        }
 
        Timer t = new Timer("Main timer", false);
        TimerTask main_task = new TimerTask(){

            @Override
            public void run() {
                if(car.backCollision() || car.frontCollision()){
                    resolveTurnRight();
                }
                
                    
                run_time.increment();
                System.out.println("Main Time: " + run_time.getValue());
            }
            
        };    
        t.scheduleAtFixedRate(main_task, 1000, 100);
                
                
            
        
        
        
        
        
    }
    
    //turns right until path is available
    public void resolveTurnRight(){
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
        Timer resolver = new Timer("Resolve Right Timer", false);
        TimerTask rrt = new TimerTask(){
            boolean clear = false;
            @Override
            public void run() {
                if(!car.frontCollision()){
                    car.setSpeed(max_speed);
                    resolver.cancel();
                
                }

                else if(!car.backCollision()){
                    car.setSpeed(-max_speed);
                    resolver.cancel();
                }

                else{
                    turnRight();
                }
                
            }
            
        };
        resolver.scheduleAtFixedRate(rrt, 5, 100);
        
    }
    
    //turns left until path is available
    public void resolveTurnLeft(){
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
    
    
    public void turnRight(){
        
        intValue time = new intValue(0);
        car.turnRight();
        Timer right_timer = new Timer("Right Timer", false);
        TimerTask right_task = new TimerTask()
        {
            
            @Override
            public void run(){
                
                time.increment();
                
                //stops turning
                if(time.getValue() > 5){
                    car.stop();
                    
                    right_timer.cancel();
                }              
                System.out.println("Right Time "+ time.getValue());
            }
        };
        right_timer.scheduleAtFixedRate(right_task, 1000, 1000);
        
    }
    
    public void turnLeft(){
        
        intValue time = new intValue(0);
        car.turnLeft();
        Timer left_timer = new Timer("Left Timer", false);
        TimerTask left_task = new TimerTask()
        {
            
            @Override
            public void run(){
                
                time.increment();
                
                //stops turning
                if(time.getValue() > 5){
                    
                    car.stop();
                    left_timer.cancel();
                    
                }              
                System.out.println("Left Time "+ time.getValue());
            }
        };
        left_timer.scheduleAtFixedRate(left_task, 1000, 1000);
     
    }

   
    
}
