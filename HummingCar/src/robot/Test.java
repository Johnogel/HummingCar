package robot;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.cmu.ri.createlab.hummingbird.HummingbirdRobot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


/**
 *
 * @author Johnogel
 */
public class Test{
public static boolean loop;   
    public static void main(String args[]){
        
        HummingbirdRobot hummingbird = new HummingbirdRobot();
        Controller car = new Controller(hummingbird);
        AutoControl auto = new AutoControl(car);
        
        auto.start();
        
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
