/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.money.Money;
import javax.persistence.*;

/**
 *
 * @author 1161213; 1161110
 */
@Entity
@DiscriminatorValue("RECHARGE")
public class Recharge extends Movement{
    
    private static final MovementDescription DESCRIPTION = MovementDescription.RECHARGE;
    
    protected Recharge(){
        //For ORM
    }
    
    public Recharge(CafeteriaUser user, Money value){
        super(user, value, DESCRIPTION);
    }
    
}
