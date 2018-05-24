/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.movement;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.ddd.DomainFactory;
import eapli.framework.domain.money.Money;
import java.util.Observable;

/**
 *
 * @author filip
 */
public class MovementBuilder extends Observable  implements  DomainFactory<Movement>{

    /**
     * Cafeteria user that made the movement
     */
     private CafeteriaUser user;
    
    /**
     * Value of the movement
     */
    private Money value;
    
    /**
     * Description of the movement
     */
    private MovementDescription description;
    
    /*Observer Pattern Implementation*/ 
    /*State Variable*/
    private int state = 0;   
    
    public MovementBuilder withCafeteriaUser(CafeteriaUser user){
        this.user = user;
        return this;
    }
    
    
    
    /**
     * Method that makes the necessary verifications to check if the value
     * of the transaction matches the type of movement.
     * 
     * @param description - description of the movement
     * @param value - value of the movement
     * @return the builder with all the new changes.
     */
    public MovementBuilder withDescriptionAndMoney(MovementDescription description, Money value){
        
        Money test = Money.euros(0.0);
        if(value.greaterThanOrEqual(test) && description.toString().equals(MovementDescription.BOOKING.toString())){
            throw new IllegalArgumentException();
        }else if(value.lessThanOrEqual(test) && !description.toString().equals(MovementDescription.BOOKING.toString())){
            throw new IllegalArgumentException();
        }
        
        this.description = description;
        this.value = value;
        
        return this;
    }
    
    @Override
    public Movement build() {
        if(description.toString().equals(MovementDescription.BOOKING.toString())){
            /*Notifies Observer classes*/
            this.state = 1;
            setChanged();
           return new Booking(user, value);
        }else if(description.toString().equals(MovementDescription.RECHARGE.toString())){
            return new Recharge(user, value);
        }else{
            return new Refund(user, value);
        }
    }
    
    public void notifyObs(){
        notifyObservers();
    }
    
}
