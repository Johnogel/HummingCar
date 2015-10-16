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
private boolean turning_right, turning_left, stopped, moving_forward, moving_backward;
public static final int 
    TURNING_LEFT = 0, 
    TURNING_RIGHT = 1, 
    STOPPED = 2, 
    MOVING_FORWARD = 3, 
    MOVING_BACKWARD = 4;

private int state = STOPPED;

private int[] sensor_values;
public static int FRONT_SENSOR = 1, BACK_SENSOR = 2, MAX_SPEED = 255;
private int front_collision_max, back_collision_max;

private boolean[] sensor_switches;
    
    public Controller(HummingbirdRobot car){
        speed = 200;
        sensor_values = new int[4];
        sensor_switches = new boolean[4];
        this.car = car;
        for(int i = 0; i < 4; i++){
            sensor_values[i] = 0;
            sensor_switches[i] = false;
        }
        
        front_collision_max = 70;
        back_collision_max = 70;
        
        turning_left = false;
        turning_right = false;
        moving_forward = false;
        moving_backward = false;
        stopped = false;
        
        
    }
    
    public HummingbirdRobot getRobot(){
        return car;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public void setLED(int id, int intensity){
        car.setLED(id, intensity);
    }
    
    public void setLEDs(boolean[] mask, int[] intensities){
        car.setLEDs(mask, intensities);
    }
    
    public void setAction(int action){
        
        //sets boolean values for information for sensors
        
        stopped = false;
        
        moving_forward = false;
        
        moving_backward = false;
        
        turning_right = false;
        
        turning_left = false;
        
        switch (action){
            
            case STOPPED:
                state = STOPPED;
                stopped = true;
                break;
                
            case MOVING_BACKWARD:
                state = MOVING_BACKWARD;
                moving_backward = true;
                break;
                
            case MOVING_FORWARD:
                state = MOVING_FORWARD;
                moving_forward = true;
                break;
                
            case TURNING_RIGHT:
                state = TURNING_RIGHT;
                turning_right = true;
                break;
                
            case TURNING_LEFT:
                state = TURNING_LEFT;
                turning_left = true;
                break;
            
            default:
                break;
                
        }
    }
    public int getState(){
        return state;
    }
    
    public boolean isStopped(){
        return stopped;
    }
    
    public boolean isTurningLeft(){
        return turning_left;
    }
    
    public boolean isTurningRight(){
        return turning_right;
    }
    
    public boolean isMovingForward(){
        return moving_forward;
    }
    
    public boolean isMovingBackward(){
        return moving_backward;
    }
    
    
    public boolean isMaxSpeed(){
        return (speed >= MAX_SPEED);
    }
    
    public boolean frontCollision(){
        if (car.getSensorValue(FRONT_SENSOR) > front_collision_max){
            
            System.out.println("FRONT COLLISION: " + car.getSensorValue(FRONT_SENSOR));
        
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
        
        int speeds[] = {-Math.abs(speed), Math.abs(speed)};
        
        boolean motors[] = {true, true};
        
        setAction(TURNING_RIGHT);
        
        car.setMotorVelocities(motors, speeds);
        
    }
    
    public void turnLeft(){
        
        int speeds[] = {Math.abs(speed), -Math.abs(speed)};
        
        boolean motors[] = {true, true};
        
        setAction(TURNING_LEFT);
        
        car.setMotorVelocities(motors, speeds);
    
    }
    
    
    
    public void stop(){
        System.out.println("STOP");
        int speeds[] = {0, 0};
        boolean motors[] = {true, true};
        setAction(STOPPED);
        this.speed = 0;
        car.setMotorVelocities(motors, speeds);
    }
    //-255 to 255
    public void setSpeed(int speed){
        int speeds[] = {speed, speed};
        boolean motors[] = {true, true};
        
        this.speed = speed;
        
        if(speed < 0){
            
            setAction(MOVING_BACKWARD);
            
        }
        
        else if (speed > 0){
            
            setAction(MOVING_FORWARD);
            
        }
        
        else{
            
            setAction(STOPPED);
            
        }
        
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
