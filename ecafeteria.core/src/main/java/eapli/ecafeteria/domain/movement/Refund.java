
package eapli.ecafeteria.domain.movement;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.money.Money;
import javax.persistence.*;

/**
 *
 * @author 1161213; 1161110
 */
@Entity
@DiscriminatorValue("REFUND")
public class Refund extends Movement{
    
    private static final MovementDescription DESCRIPTION = MovementDescription.REFUND;
    
    protected Refund(){
        //For ORM
    }
    
    public Refund(CafeteriaUser user, Money value){
        super(user, value, DESCRIPTION);
    }
    
}
