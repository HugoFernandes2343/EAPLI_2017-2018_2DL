/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author losa
 */
public class ListCafeteriaUsersController {

    private final CafeteriaUserRepository repo = PersistenceContext.repositories().cafeteriaUsers();

    public Iterable<CafeteriaUser> activeCafeteriaUsers() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        return this.repo.findAllActive();
    }
}
