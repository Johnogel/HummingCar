package robot;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.cmu.ri.createlab.hummingbird.HummingbirdRobot;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;



/**
 *
 * @author Johnogel
 */
public class Test{
public static boolean loop;   
private static String ttyConfig;
public static int speed;

    public static void main(String args[]) throws InterruptedException{
        
        HummingbirdRobot hummingbird = new HummingbirdRobot();
        Controller car = new Controller(hummingbird);
        
        //AutoControl auto = new AutoControl(car);
        
        Random gen = new Random();
//        hummingbird.setMotorVelocity(1, 100);
//        Thread.sleep(100000);
//        car.stop();
        
        //ExecutorService executor = Executors.newCachedThreadPool();
        
        System.out.println("About to start\n");
        //auto.start();
        
        CarTaskManager manager = new CarTaskManager(car);
        //manager.initialize();
        
        UserController ui = new UserController(manager);
        ui.initialize();
        
        
        
        
        
        //hummingbird.setLED(4, 0);
        //car.turnRight();
        //Thread.sleep(1000000);
        //car.stop();
//        intValue time = new intValue(0);
//        ActionListener listener = new ActionListener()
//        {
//            @Override
//            public void actionPerformed(ActionEvent event)
//            {              
//                
//                if ((!car.frontCollision() || !car.backCollision()) && !car.isMaxSpeed()){
//                    car.setSpeed(Controller.MAX_SPEED);
//                }
//                
//                time.increment();
//               
//                if (time.getValue() > 60){
//                    car.turnRight();
//                }
//                
//                if(car.frontCollision() || car.backCollision()){
//                    car.stop();
//                }
//            }
//        };
        
//        Timer t = new Timer(1000, listener);
//        t.start();
    }
    
    
    
}
