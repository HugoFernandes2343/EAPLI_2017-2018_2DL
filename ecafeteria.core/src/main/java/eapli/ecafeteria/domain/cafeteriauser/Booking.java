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
@DiscriminatorValue("BOOKING")
public class Booking extends Movement{
    
    private static final MovementDescription DESCRIPTION = MovementDescription.BOOKING;
    
    protected Booking(){
        //For ORM
    }
    
    public Booking(CafeteriaUser user, Money value){
        super(user, value, DESCRIPTION);
    }
}
