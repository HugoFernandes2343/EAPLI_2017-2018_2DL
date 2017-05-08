/**
 *
 */
package eapli.ecafeteria.domain.cafeteria;

import eapli.framework.domain.ddd.AggregateRoot;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

/**
 * A Cafeteria.
 *
 * @author SOU03408
 *
 */
@Entity
public class Cafeteria implements AggregateRoot<CafeteriaName> {

    @Version
    private Long version;

    @EmbeddedId
    private CafeteriaName name;

    protected Cafeteria() {
        // for ORM
    }

    public Cafeteria(String name, String description, OrganicUnit unit) {
        this.name = new CafeteriaName(name);
        // TODO implement rest of the method
    }

    @Override
    public boolean sameAs(Object other) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean is(CafeteriaName id) {
        return this.name.equals(id);
    }

    @Override
    public CafeteriaName id() {
        return this.name;
    }
}
