
package eapli.ecafeteria.domain.movement;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
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
