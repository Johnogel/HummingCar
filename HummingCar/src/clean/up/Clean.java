/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clean.up;

import edu.cmu.ri.createlab.hummingbird.HummingbirdRobot;

/**
 *
 * @author Johnogel
 */
public class Clean {
    
    public static void main(String[] args){
        HummingbirdRobot car = new HummingbirdRobot();
        car.emergencyStop();
        System.out.println("Carried out Emergency Stop");
    }
    
}
