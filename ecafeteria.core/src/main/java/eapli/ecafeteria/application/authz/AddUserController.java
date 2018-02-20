package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.SystemUserBuilder;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.Set;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class AddUserController implements Controller {

    private final UserRepository userRepository = PersistenceContext.repositories().users(null);

    /**
     * Get existing RoleTypes available to the user.
     *
     * @return a list of RoleTypes
     */
    public RoleType[] getRoleTypes() {
        return RoleType.nonUserValues();
    }

    public SystemUser addUser(String username, String password, String firstName, String lastName, String email,
            Set<RoleType> roles, Calendar createdOn) throws DataIntegrityViolationException, DataConcurrencyException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        final SystemUserBuilder userBuilder = new SystemUserBuilder();
        userBuilder.withUsername(username).withPassword(password).withFirstName(firstName).withLastName(lastName)
                .withEmail(email).withCreatedOn(createdOn).withRoles(roles);

        return this.userRepository.save(userBuilder.build());
    }

    public SystemUser addUser(String username, String password, String firstName, String lastName, String email,
            Set<RoleType> roles) throws DataIntegrityViolationException, DataConcurrencyException {
        return addUser(username, password, firstName, lastName, email, roles, DateTime.now());
    }
}
