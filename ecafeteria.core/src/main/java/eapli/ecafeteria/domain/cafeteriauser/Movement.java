package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 *
 * @author 1161213; 1161110
 */
@Entity
public class Movement implements AggregateRoot<Long>, Serializable{
    
    /**
     * Attribute to keep the id of the movement
     */
    @Id
    @GeneratedValue
    private Long id;
    
    /**
     * Mecanographic Number of the person who made the movement
     */
    @ManyToOne()
    private CafeteriaUser user;
    
    /**
     * Value of the movement
     */
    private double value;
    
    /**
     * Date where the movement was made.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    /**
     * Description of the movement.
     */
    @Enumerated(EnumType.STRING)
    private MovementDescription description;
    
    protected Movement(){
       //for ORM 
    }
    
    /**
     * @param user - user that made the movement
     * @param value - value of the movement
     * @param description - description of the movement
     */
    public Movement(CafeteriaUser user, double value, MovementDescription description){
        
        if(user == null || description == null || description == null){
            throw new IllegalArgumentException();
        }
        
        this.user = user;
        this.value = value;
        this.date = new Date();
        this.description = description;
    }
    
    @Override
    public boolean sameAs(Object other) {
          if (!(other instanceof Movement)) {
            return false;
        }

        final Movement that = (Movement) other;
        if (this == that) {
            return true;
        }
        
        return this.date.equals(that.date) && this.user.sameAs(that.user) && this.value == that.value;
    }

    @Override
    public boolean is(Long otherId) {
        return this.id.equals(otherId);
    }

    @Override
    public Long id() {
        return this.id;
    }
}
