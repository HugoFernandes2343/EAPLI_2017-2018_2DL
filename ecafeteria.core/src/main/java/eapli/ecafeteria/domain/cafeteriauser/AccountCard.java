package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author 1161110 & 1161213
 */
@Embeddable
public class AccountCard implements ValueObject, Serializable{
    
    private static final long serialVersionUID = 1L;
    
    //every user starts with 0€ on balance 
    private static final double INITIAL_BALANCE = 0;
    
    //initial balance limit for warnings, must be configured
    private static final double INITIAL_BALANCE_LIMIT = -1;

    @Version
    private Long version;
    
    @Transient
    private double balance;
    
    
    private double balance_limit;
    
    /**
     * New Account Card Constructor and for ORM use
     */
    public AccountCard() {
        this.balance = INITIAL_BALANCE;
        this.balance_limit = INITIAL_BALANCE_LIMIT;
    }
    
    
    
}
