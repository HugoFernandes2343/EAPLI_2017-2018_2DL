package eapli.ecafeteria.domain.authz.exceptions;

/**
 * Created by nuno on 22/03/16.
 */
public class UserSessionNotInitiatedException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public UserSessionNotInitiatedException() {
		super();
	}

	public UserSessionNotInitiatedException(String message) {
		super(message);
	}
}
