/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;

/**
 *
 * @author Fernando
 */
public class DeactivateUserController implements Controller {

    private final UserRepository userRepository = PersistenceContext.repositories().users(null);

    public Iterable<SystemUser> activeUsers() {
	Application.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

	return this.userRepository.findAll();
    }

    public SystemUser deactivateUser(SystemUser user) throws DataConcurrencyException, DataIntegrityViolationException {
	Application.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

	user.deactivate(DateTime.now());
	return this.userRepository.save(user);
    }
}
