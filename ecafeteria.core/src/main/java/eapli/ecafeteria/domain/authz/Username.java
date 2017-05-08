/**
 *
 */
package eapli.ecafeteria.domain.authz;

import eapli.framework.domain.ddd.ValueObject;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * a username.
 *
 * it must not be empty
 *
 * @author Paulo Gandra Sousa
 */
@Embeddable
public class Username implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;
    private String name;

    public Username(String username) {
        if (Strings.isNullOrEmpty(username)) {
            throw new IllegalStateException("username should neither be null nor empty");
        }
        // TODO validate other invariants, e.g., regular expression
        this.name = username;
    }

    protected Username() {
        // for ORM
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Username)) {
            return false;
        }

        final Username other = (Username) o;
        return this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
