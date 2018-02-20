package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.AuthenticationService;
import eapli.ecafeteria.domain.authz.Password;
import eapli.ecafeteria.domain.authz.UserSession;
import eapli.ecafeteria.domain.authz.Username;
import eapli.framework.application.Controller;
import java.util.Optional;

/**
 * Created by nuno on 21/03/16.
 */
public class LoginController implements Controller {

    private final AuthenticationService authenticationService = new AuthenticationService();

    /**
     * This method allows a user to perform login and creates the session.
     *
     * @param userName
     * @param password
     */
    public boolean login(String userName, String password, ActionRight... onlyWithThis) {
        try {
            final Optional<UserSession> newSession = authenticationService.authenticate(new Username(userName),
                    new Password(password), onlyWithThis);
            if (newSession.isPresent()) {
                AuthorizationService.setSession(newSession.get());
            }
            return newSession.isPresent();
        } catch (final IllegalStateException e) {
            return false;
        }
    }
}
