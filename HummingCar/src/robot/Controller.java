package robot;


import edu.cmu.ri.createlab.hummingbird.HummingbirdRobot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnogel
 */
public class Controller implements KeyListener{
private HummingbirdRobot car;
private int speed;

private int[] sensor_values;
public static int FRONT_SENSOR = 1, BACK_SENSOR = 2, MAX_SPEED = 255;
private int front_collision_max, back_collision_max;

private boolean[] sensor_switches;
    
    public Controller(HummingbirdRobot car){
        sensor_values = new int[4];
        sensor_switches = new boolean[4];
        this.car = car;
        for(int i = 0; i < 4; i++){
            sensor_values[i] = 0;
            sensor_switches[i] = false;
        }
        front_collision_max = 100;
        back_collision_max = 100;
        
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public boolean isMaxSpeed(){
        return (speed >= MAX_SPEED);
    }
    
    public boolean frontCollision(){
        if (car.getSensorValue(FRONT_SENSOR) > front_collision_max){
            System.out.println("FRONT COLLISION");
        
            return true;
        }
        return false;
    }
    
    public boolean backCollision(){
        if(car.getSensorValue(BACK_SENSOR) > back_collision_max){
            System.out.println("BACK COLLISION");
            return true;
        }
        return false;
    }
    
    public void turnRight(){
        System.out.println("(RIGHT TURN) Speed: " + speed);
        int speeds[] = {-speed, speed};
        boolean motors[] = {true, true};
        
        car.setMotorVelocities(motors, speeds);
    }
    
    public void turnLeft(){
        int speeds[] = {speed, -speed};
        boolean motors[] = {true, true};
        
        car.setMotorVelocities(motors, speeds);
    
    }
    
    
    
    public void stop(){
        System.out.println("STOP");
        int speeds[] = {0, 0};
        boolean motors[] = {true, true};
        
        this.speed = 0;
        car.setMotorVelocities(motors, speeds);
    }
    //-255 to 255
    public void setSpeed(int speed){
        int speeds[] = {speed, speed};
        boolean motors[] = {true, true};
        
        this.speed = speed;
        car.setMotorVelocities(motors, speeds);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        System.out.println("Key Typed: "+ke.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        System.out.println("Key Pressed: "+ke.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        System.out.println("Key Released: "+ke.getKeyChar());
    }
    
}
