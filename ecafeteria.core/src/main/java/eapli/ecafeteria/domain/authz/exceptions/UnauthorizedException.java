/**
 *
 */
package eapli.ecafeteria.domain.authz.exceptions;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;

/**
 * @author Paulo Gandra Sousa
 *
 */
public class UnauthorizedException extends RuntimeException {

    private final SystemUser user;
    private final ActionRight[] actions;

    /**
     * @param message
     */
    public UnauthorizedException(String message, SystemUser user, ActionRight... actions) {
        super(message);
        this.actions = actions;
        this.user = user;
    }

    public SystemUser user() {
        return this.user;
    }

    public ActionRight[] intendedActions() {
        return this.actions;
    }
}
