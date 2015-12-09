package robot.support;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johnogel
 */
public class WrapperValue {
    private int value;
    private boolean bool;
    
    
    public WrapperValue(){
        
    }
    
    public void setIntValue(int value){
        this.value = value;
    }
    
    public int getIntValue(){
        return value;
    }
    
    public void setBooleanValue(boolean bool){
        this.bool = bool;
        
    }
    public boolean getBooleanValue(){
        return bool;
    }
    
    public void increment(){
        value++;
    }
}
