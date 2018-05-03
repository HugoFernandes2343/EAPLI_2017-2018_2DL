package eapli.ecafeteria.domain.movement;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.domain.money.Money;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 *
 * @author 1161213; 1161110
 */
@Entity
@Table(name="MOVEMENT")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type")
public abstract class Movement implements AggregateRoot<Long>, Serializable{
    
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
    private Money value;
    
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
    public Movement(CafeteriaUser user, Money value, MovementDescription description){
        Money test = Money.euros(0.0);
        if(user == null || description == null || description == null){
            throw new IllegalArgumentException();
        }
        
        if(value.greaterThanOrEqual(test) && description.toString().equals(MovementDescription.BOOKING.toString())){
            throw new IllegalArgumentException();
        }else if(value.lessThanOrEqual(test) && !description.toString().equals(MovementDescription.BOOKING.toString())){
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
        
        return this.id.equals(that.id);
    }

    @Override
    public boolean is(Long otherId) {
        return this.id.equals(otherId);
    }

    @Override
    public Long id() {
        return this.id;
    }
    
    public CafeteriaUser madeBy(){
        return this.user;
    }
    
    public double value(){
        return this.value.amount();
    }
}
