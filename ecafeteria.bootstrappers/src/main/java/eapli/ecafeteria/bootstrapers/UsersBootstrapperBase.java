package eapli.ecafeteria.bootstrapers;

import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import eapli.ecafeteria.application.authz.AddUserController;
import eapli.ecafeteria.application.authz.ListUsersController;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class UsersBootstrapperBase {

    final AddUserController userController = new AddUserController();
    final ListUsersController listUserController = new ListUsersController();

    public UsersBootstrapperBase() {
        super();
    }

    /**
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param roles
     */
    protected SystemUser registerUser(final String username, final String password,
            final String firstName, final String lastName, final String email,
            final Set<RoleType> roles) {
        SystemUser u = null;
        try {
            u = userController.addUser(username, password, firstName, lastName, email, roles);
            Logger.getLogger(MasterUsersBootstrapper.class.getSimpleName()).log(Level.FINE,
                    "»»» " + username);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // assuming it is just a primary key violation due to the tentative
            // of inserting a duplicated user. let's just lookup that user
            final Optional<SystemUser> ou = listUserController.find(new Username(username));
            assert ou.isPresent();
            u = ou.get();
        }
        return u;
    }
}
