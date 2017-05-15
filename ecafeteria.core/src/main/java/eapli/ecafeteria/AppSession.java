package eapli.ecafeteria;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.UserSession;
import eapli.ecafeteria.domain.authz.exceptions.UnauthorizedException;
import eapli.ecafeteria.domain.authz.exceptions.UserSessionNotInitiatedException;

/**
 * the application session.
 *
 * @author Paulo Gandra Sousa
 */
public class AppSession {

    private UserSession theSession = null;

    // in a real life situation this method should not exist! anyone could
    // circunvent the authentication
    public void setSession(UserSession session) {
        this.theSession = session;
    }

    public void clearSession() {
        this.theSession = null;
    }

    public UserSession session() {
        return this.theSession;
    }

    public void ensurePermissionOfLoggedInUser(ActionRight... actions) {
        if (session() == null) {
            throw new UserSessionNotInitiatedException();
        }
        if (!session().authenticatedUser().isAuthorizedTo(actions)) {
            throw new UnauthorizedException("User is not authorized to perform this action",
                    session().authenticatedUser(), actions);
        }
    }
}
