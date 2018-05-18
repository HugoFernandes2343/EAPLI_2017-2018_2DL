/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author filip
 */
@Embeddable
public class BalanceLimit implements ValueObject, Serializable{
    
    private double value;
            
    public BalanceLimit(){
        this.value = -1;
    }
    
    public void defineLimit(double value){
        
        if(value <= 0.0){
            throw new IllegalArgumentException("Not possible to define a limit with a negative value.");
        }
        
        this.value = value;
        
    }
}