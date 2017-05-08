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

/**
 *
 * @author losa
 */
public class ListUsersController implements Controller {

    private final UserRepository userRepository = PersistenceContext.repositories().users(null);

    public Iterable<SystemUser> allUsers() {
	Application.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

	return this.userRepository.findAll();
    }
}
