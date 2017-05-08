/**
 *
 */
package eapli.ecafeteria.domain.cafeteria;

import eapli.framework.domain.ddd.ValueObject;
import eapli.util.Strings;
import javax.persistence.Embeddable;

/**
 * @author SOU03408
 *
 */
@Embeddable
public class CafeteriaName implements ValueObject {

    private static final long serialVersionUID = 1L;
    private String name;

    protected CafeteriaName() {
        // for ORM
    }

    public CafeteriaName(String name) {
        if (Strings.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("cafetria name cannot be empty neither null");
        }
        setName(name);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof CafeteriaName)) {
            return false;
        }
        final CafeteriaName other = (CafeteriaName) o;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
