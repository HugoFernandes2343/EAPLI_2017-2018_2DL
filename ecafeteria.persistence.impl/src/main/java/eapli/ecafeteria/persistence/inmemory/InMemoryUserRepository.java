package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryUserRepository extends InMemoryRepository<SystemUser, Username> implements UserRepository {

    @Override
    protected Username newPK(SystemUser u) {
        return u.username();
    }
}
