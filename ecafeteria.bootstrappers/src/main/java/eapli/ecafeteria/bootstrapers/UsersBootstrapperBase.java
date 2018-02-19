package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.authz.AddUserController;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersBootstrapperBase {

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
    protected SystemUser registerUser(final String username, final String password, final String firstName,
            final String lastName, final String email, final Set<RoleType> roles) {
        final AddUserController userController = new AddUserController();
        SystemUser u = null;
        try {
            u = userController.addUser(username, password, firstName, lastName, email, roles);
            Logger.getLogger(MasterUsersBootstrapper.class.getSimpleName()).log(Level.FINE, "»»» " + username);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(MasterUsersBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
        return u;
    }
}
