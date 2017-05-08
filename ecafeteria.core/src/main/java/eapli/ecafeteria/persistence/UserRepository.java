package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.framework.persistence.repositories.DataRepository;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 * Created by nuno on 21/03/16.
 */
public interface UserRepository extends DataRepository<SystemUser, Username> {

}
