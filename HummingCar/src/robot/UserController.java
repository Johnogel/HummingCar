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
    
   
    public static int W = 0x77, D = 0x64, S = 0x73, A = 0x61, I = 0x69, SPACE = 0x20, ESCAPE = 0x1B, T = 0x74,
                      UP = 0x26, DOWN = 0x28, RIGHT = 0x27, LEFT = 0x25;

    public UserController(CarTaskManager manager) {
        this.manager = manager;
        this.car = manager.getCar();
        speed = 255;
    }

    public void initialize() throws InterruptedException {
    manager.initialize();

        try {
            setTerminalToCBreak();

            int i = 0;
            while (true) {

                //System.out.println( ""+ i++ );
                manager.updateAuto();
                if (System.in.available() != 0) {
                    int c = System.in.read();

                    //escape
                    

                    //w
                    if (c == W) {
                        System.out.println("FORWARD");
                        car.setSpeed(speed);
                    }

                    //d
                    else if (c == D) {
                        System.out.println("RIGHT");
                        car.turnRight();
                    }

                    //s
                    else if (c == S) {
                        System.out.println("REVERSE");
                        car.setSpeed(-speed);
                    }

                    //a
                    else if (c == A) {
                        System.out.println("LEFT");
                        car.turnLeft();
                    }

                    //space
                    else if (c == SPACE) {
                        System.out.println("STOP");
                        car.stop();
                    }

                    else if (c == T) {
                        System.out.println("TOGGLE AUTO");
                        manager.toggleAuto();
                    }
                    //i
                    else if (c == I) {
                        System.out.println("INTERRUPT");
                        manager.stop(CarTaskManager.LED);
                        break;
                    }
                    
                    else if (c == UP){
                        System.out.println("SPEED UP");
                        incrementSpeed();
                    }
                    
                    else if (c == DOWN){
                        System.out.println("SPEED DOWN");
                        decrementSpeed();
                    }
                    
                    else if (c == ESCAPE) {
                        System.out.println("OOGAALOOGAABOOGGAA");
                        break;
                    }
                    
                    else {
                        car.stop();
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
    }

    public void decrementSpeed() {
        if (speed > 5) {
            speed -= 5;
        }
    }

}
