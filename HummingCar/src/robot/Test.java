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
        manager.initialize();
        
        
        try {
                setTerminalToCBreak();

                int i=0;
                while (true) {

                        //System.out.println( ""+ i++ );

                        if ( System.in.available() != 0 ) {
                                int c = System.in.read();
                                
                                //escape
                                if ( c == 0x1B ) {
                                    System.out.println("OOGAALOOGAABOOGGAA");
                                    break;
                                }
                                
                                //w
                                if (c == 0x77){
                                    System.out.println("FORWARD");
                                    car.setSpeed(200);
                                }
                                
                                //d
                                if (c == 0x64){
                                    System.out.println("RIGHT");
                                    car.turnRight();
                                }
                                
                                //s
                                if (c == 0x73){
                                    System.out.println("REVERSE");
                                    car.setSpeed(-200);
                                }
                                
                                //a
                                if (c == 0x61){
                                    System.out.println("LEFT");
                                    car.turnLeft();
                                }
                                
                                //space
                                if (c == 0x20){
                                    System.out.println("STOP");
                                    car.stop();
                                }
                                
                                //i
                                if (c == 0x69){
                                    System.out.println("INTERRUPT");
                                    manager.stop(CarTaskManager.LED);
                                }
                        }

                } // end while
            }
            catch (IOException e) {
                    System.err.println("IOException");
            }
            catch (InterruptedException e) {
                    System.err.println("InterruptedException");
            }
            finally {
                try {
                    stty( ttyConfig.trim() );
                 }
                 catch (Exception e) {
                     System.err.println("Exception restoring tty config");
                 }
            }
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

  
    private static void setTerminalToCBreak() throws IOException, InterruptedException {

        ttyConfig = stty("-g");

        // set the console to be character-buffered instead of line-buffered
        stty("-icanon min 1");

        // disable character echoing
        stty("-echo");
    }

    /**
     *  Execute the stty command with the specified arguments
     *  against the current active terminal.
     */
    private static String stty(final String args)
                    throws IOException, InterruptedException {
        String cmd = "stty " + args + " < /dev/tty";

        return exec(new String[] {
                    "sh",
                    "-c",
                    cmd
                });
    }

    /**
     *  Execute the specified command and return the output
     *  (both stdout and stderr).
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
    
    
    
}
