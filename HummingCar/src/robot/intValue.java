package robot;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnogel
 */
public class intValue {
    private int value;
    
    public intValue(int value){
        this.value = value;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
    
    public void increment(){
        value++;
    }
}
