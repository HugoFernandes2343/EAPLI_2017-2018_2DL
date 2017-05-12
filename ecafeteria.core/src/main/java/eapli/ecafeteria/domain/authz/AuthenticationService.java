package eapli.ecafeteria.domain.authz;

import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import java.util.Optional;

/**
 *
 * @author Paulo Gandra Sousa
 *
 */
public class AuthenticationService {

    private final UserRepository repo = PersistenceContext.repositories().users(null);

    /**
     * Checks if a user can be authenticated with the username/password pair
     *
     * @param username
     * @param pass
     * @return the authenticated user or null otherwise
     */
    public Optional<UserSession> authenticate(Username username, Password pass, ActionRight... onlyWithThis) {
        if (username == null) {
            throw new IllegalArgumentException("a username must be provided");
        }
        final Optional<SystemUser> user = retrieveUser(username);
        if (!user.isPresent()) {
            return Optional.empty();
        }
        if (user.get().passwordMatches(pass) && user.get().isActive()) {
            if (anyActionRight(onlyWithThis) || user.get().isAuthorizedTo(onlyWithThis)) {
                return Optional.of(createSessionForUser(user.get()));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    private boolean anyActionRight(ActionRight... onlyWithThis) {
        return onlyWithThis.length == 0 || (onlyWithThis.length == 1 && onlyWithThis[0] == null);
    }

    private UserSession createSessionForUser(SystemUser user) {
        return new UserSession(user);
    }

    private Optional<SystemUser> retrieveUser(Username userName) {
        return this.repo.findOne(userName);
    }
}
