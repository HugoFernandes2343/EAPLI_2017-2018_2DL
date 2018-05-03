
package eapli.ecafeteria.domain.movement;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
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
