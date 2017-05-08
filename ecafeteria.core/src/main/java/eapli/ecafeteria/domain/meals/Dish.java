package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.Designation;
import eapli.framework.domain.Money;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

/**
 * A Dish
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 */
@Entity
// @Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Dish implements AggregateRoot<Designation>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private Designation name;
    @ManyToOne(cascade = CascadeType.MERGE)
    private DishType dishType;
    private NutricionalInfo nutricionalInfo;
    private Money price;
    private boolean active;

    public Dish(final DishType dishType, final Designation name, final NutricionalInfo nutricionalInfo, Money price) {
        if (dishType == null || name == null || nutricionalInfo == null) {
            throw new IllegalStateException();
        }

        this.dishType = dishType;
        this.name = name;
        this.nutricionalInfo = nutricionalInfo;
        this.setPrice(price);
        this.active = true;
    }

    public Dish(final DishType dishType, final Designation name, Money price) {
        if (dishType == null || name == null || price == null) {
            throw new IllegalStateException();
        }

        this.dishType = dishType;
        this.name = name;
        this.nutricionalInfo = null;
        this.price = price;
        this.active = true;
    }

    protected Dish() {
        // for ORM only
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Dish)) {
            return false;
        }

        final Dish other = (Dish) o;
        return id().equals(other.id());
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Dish)) {
            return false;
        }

        final Dish that = (Dish) other;
        if (this == that) {
            return true;
        }

        return id().equals(that.id()) && dishType.equals(that.dishType) && nutricionalInfo.equals(that.nutricionalInfo)
                && price.equals(that.price) && active == that.active;
    }

    @Override
    public boolean is(Designation id) {
        return id().equals(id);
    }

    public DishType dishType() {
        return this.dishType;
    }

    @Override
    public Designation id() {
        return this.name;
    }

    public NutricionalInfo nutricionalInfo() {
        return this.nutricionalInfo;
    }

    public Designation name() {
        return this.name;
    }

    public Money currentPrice() {
        return this.price;
    }

    /**
     *
     * @return true or false whether is or not active
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * toggles the state of the dish, activating it or deactivating it
     * accordingly.
     *
     * @return whether the dish is active or not
     */
    public boolean toogleState() {
        this.active = !this.active;
        return isActive();
    }

    /**
     * Changes the nutritional info of the dish
     *
     * @param newNutricionalInfo
     */
    public void changeNutricionalInfoTo(NutricionalInfo newNutricionalInfo) {
        if (newNutricionalInfo == null) {
            throw new IllegalArgumentException();
        }
        this.nutricionalInfo = newNutricionalInfo;
    }

    public void changePriceTo(Money newPrice) {
        // TODO extra business logic associated with changing the price of a
        // dish, e.g., save price history
        setPrice(newPrice);
    }

    private void setPrice(Money price) {
        if (price == null || price.lessThanOrEqual(Money.euros(0))) {
            throw new IllegalArgumentException();
        }
        this.price = price;
    }
}
