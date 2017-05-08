/**
 *
 */
package eapli.ecafeteria.domain.authz;

import eapli.framework.domain.ddd.ValueObject;
import java.util.UUID;

/**
 * @author Paulo Gandra Sousa
 *
 */
public class UserSession implements ValueObject {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final SystemUser user;
    private final UUID token;

    public UserSession(SystemUser user) {
        if (user == null) {
            throw new IllegalStateException("user must not be null");
        }
        this.user = user;
        this.token = UUID.randomUUID();
    }

    public SystemUser authenticatedUser() {
        return this.user;
    }

    @Override
    public String toString() {
        return this.user.id() + "@" + this.token;
    }
}
