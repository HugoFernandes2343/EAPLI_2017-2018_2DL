package eapli.ecafeteria.domain.authz.exceptions;

/**
 * Created by nuno on 22/03/16.
 */
public class UserSessionNotInitiatedException extends RuntimeException {

    public UserSessionNotInitiatedException() {
        super();
    }

    public UserSessionNotInitiatedException(String message) {
        super(message);
    }
}
