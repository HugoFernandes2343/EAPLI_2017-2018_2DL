/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import java.util.Optional;

/**
 *
 * @author losa
 */
public class ListUsersController implements Controller {

    private final UserRepository userRepository = PersistenceContext.repositories().users(null);

    public Iterable<SystemUser> allUsers() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        return this.userRepository.findAll();
    }

    public Optional<SystemUser> find(Username u) {
        return userRepository.findOne(u);
    }
}
