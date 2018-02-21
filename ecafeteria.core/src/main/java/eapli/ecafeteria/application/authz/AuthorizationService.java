package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.UserSession;
import eapli.ecafeteria.domain.authz.exceptions.UnauthorizedException;
import eapli.ecafeteria.domain.authz.exceptions.UserSessionNotInitiatedException;
import java.util.Optional;

/**
 * helper "singleton" session holder the application session.
 *
 * @author Paulo Gandra Sousa
 */
public class AuthorizationService {

    private static UserSession theSession = null;

    // in a real life situation this method should not exist! anyone could
    // circunvent the authentication
    public static void setSession(UserSession session) {
        if (session == null) {
            throw new IllegalArgumentException();
        }
        theSession = session;
    }

    // in a real life situation this method should not exist! anyone could
    // circunvent the authentication
    public static void setSession(Optional<UserSession> session) {
        if (session == null) {
            throw new IllegalArgumentException();
        }
        if (session.isPresent()) {
            theSession = session.get();
        } else {
            clearSession();
        }
    }

    public static void clearSession() {
        theSession = null;
    }

    public static UserSession session() {
        return theSession;
    }

    public static void ensurePermissionOfLoggedInUser(ActionRight... actions) {
        if (session() == null) {
            throw new UserSessionNotInitiatedException();
        }
        if (!session().authenticatedUser().isAuthorizedTo(actions)) {
            throw new UnauthorizedException("User is not authorized to perform this action",
                    session().authenticatedUser(), actions);
        }
    }
}
