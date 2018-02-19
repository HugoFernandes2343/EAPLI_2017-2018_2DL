/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.UserSession;
import eapli.framework.actions.Action;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Paulo Gandra Sousa
 */
public class MasterUsersBootstrapper extends UsersBootstrapperBase implements Action {

    @Override
    public boolean execute() {
        registerPowerUser();
        registerAdmin("admin", TestDataConstants.PASSWORD1, "Jane", "Doe Admin", "jane.doe@email.local");
        return true;
    }

    private void registerPowerUser() {
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.ADMIN);
        roles.add(RoleType.MENU_MANAGER);
        roles.add(RoleType.KITCHEN_MANAGER);
        roles.add(RoleType.CASHIER);
        roles.add(RoleType.CAFETERIA_USER);

        final SystemUser poweruser = registerUser("poweruser", TestDataConstants.PASSWORD1, "joe", "doe",
                "joe@email.org", roles);

        // authenticate a super user to be able to register new users, and
        // perform all other options during bootstrap
        // in this case we will inject the session but we shouldn't do this
        final UserSession adminSession = new UserSession(poweruser);
        Application.session().setSession(adminSession);
    }

    /**
     *
     */
    private void registerAdmin(final String username, final String password, final String firstName,
            final String lastName, final String email) {
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.ADMIN);

        registerUser(username, password, firstName, lastName, email, roles);
    }
}
