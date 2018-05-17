package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.ddd.DomainEntity;
import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.GeneratedValue;

/**
 *
 * @author 1161110 & 1161213
 */
@Entity
public class AccountCard implements Serializable, DomainEntity<Long>{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
        
    private BalanceLimit balanceLimit;
    
//    protected AccountCard(){
//        //for ORM
//    }
    
    /**
     * New Account Card Constructor and for ORM use
     */
    public AccountCard() {
        this.balanceLimit = new BalanceLimit();
    }

    @Override
    public boolean is(Long otherId) {
        return this.id.compareTo(otherId) == 0;
    }

    @Override
    public Long id() {
        return this.id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
