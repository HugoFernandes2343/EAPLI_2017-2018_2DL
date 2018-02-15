/**
 *
 */
package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.AuthenticationService;
import eapli.ecafeteria.domain.authz.Password;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * @author Paulo Gandra de Sousa
 *
 */
public class ChangePasswordController implements Controller {
	private final AuthenticationService authenticationService = new AuthenticationService();

	public boolean changePassword(String oldPassword, String newPassword) throws DataConcurrencyException, DataIntegrityViolationException {
		return authenticationService.changePassword(Application.session().session().authenticatedUser(), new Password(oldPassword), new Password(newPassword));
	}
}
