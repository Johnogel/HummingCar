/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Johnogel
 */
public class UserController {

    private static String ttyConfig;
    private CarTaskManager manager;
    private Controller car;
    private int speed;
    
   
    public final static int 
            W = 0x77,
            D = 0x64, 
            S = 0x73, 
            A = 0x61, 
            I = 0x69, 
            SPACE = 0x20, 
            ESCAPE = 0x1B, 
            T = 0x74,          
            UP = 0x26, 
            DOWN = 0x28, 
            RIGHT = 0x27, 
            LEFT = 0x25, 
            MINUS = 0x2d, 
            PLUS = 0x2B, 
            EQUAL = 0x3d;

    public UserController(CarTaskManager manager) {
        this.manager = manager;
        this.car = manager.getCar();
        speed = 255;
    }

    public void initialize() throws InterruptedException {
    manager.initialize();

        try {
            setTerminalToCBreak();
            
            
            boolean loop = true;
            int i = 0;
            while (loop) {

                //System.out.println( ""+ i++ );
                manager.updateAuto();
                if (System.in.available() != 0) {
                    int c = System.in.read();


                    switch (c){
                        case W:
                            System.out.println("FORWARD");
                            car.setSpeed(speed);
                            break;
                            
                        case D:
                            System.out.println("RIGHT");
                            car.turnRight();
                            break;
                            
                        case A:
                            System.out.println("LEFT");
                            car.turnLeft();
                            break;
                            
                        case S:
                            System.out.println("REVERSE");
                            car.setSpeed(-speed);
                            break;
                            
                        case T:
                            System.out.println("TOGGLE AUTO");
                            manager.toggleAuto();
                            break;
                            
                        case I:
                            System.out.println("RIGHT");
                            car.turnRight();
                            break;
                            
                        case SPACE:
                            System.out.println("STOP");
                            car.stop();
                            break;
                            
                        case EQUAL: case PLUS:
                            System.out.println("SPEED UP");
                            incrementSpeed();
                            break;
                        
                        case MINUS:
                            System.out.println("SLOW DOWN");
                            decrementSpeed();
                            break;
                            
                        case ESCAPE:
                            System.out.println("OOGAALOGABOOGA");
                            car.stop();
                            loop = false;
                            break;
                            
                        default:
                            break;
                    }
                   
                }

            } // end while
        } catch (IOException e) {
            System.err.println("IOException");
            
        } catch (InterruptedException e) {
            System.err.println("InterruptedException");
            
        } finally {
            try {
                stty(ttyConfig.trim());
            } catch (Exception e) {
                System.err.println("Exception restoring tty config");
            }
        }

    }

    private static void setTerminalToCBreak() throws IOException, InterruptedException {

        ttyConfig = stty("-g");

        // set the console to be character-buffered instead of line-buffered
        stty("-icanon min 1");

        // disable character echoing
        stty("-echo");
    }

    /**
     * Execute the stty command with the specified arguments against the current
     * active terminal.
     */
    private static String stty(final String args)
            throws IOException, InterruptedException {
        String cmd = "stty " + args + " < /dev/tty";

        return exec(new String[]{
            "sh",
            "-c",
            cmd
        });
    }

    /**
     * Execute the specified command and return the output (both stdout and
     * stderr).
     */
    private static String exec(final String[] cmd)
            throws IOException, InterruptedException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        Process p = Runtime.getRuntime().exec(cmd);
        int c;
        InputStream in = p.getInputStream();

        while ((c = in.read()) != -1) {
            bout.write(c);
        }

        in = p.getErrorStream();

        while ((c = in.read()) != -1) {
            bout.write(c);
        }

        p.waitFor();

        String result = new String(bout.toByteArray());
        return result;
    }

    public void incrementSpeed() {
        if (speed < 250) {
            speed += 5;
        }
        
        updateSpeed();
        
    }

    public void decrementSpeed() {
        if (speed > 5) {
            speed -= 5;
        }
        updateSpeed();
        
    }
    
    private void updateSpeed(){
        if(car.getState() == Controller.MOVING_FORWARD){
            car.setSpeed(speed);
        }
        
        else if(car.getState() == Controller.MOVING_BACKWARD){
            car.setSpeed(-speed);
        }
    }

}
