package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.SignupRequest;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class InMemorySignupRequestRepository extends InMemoryRepository<SignupRequest, Username>
        implements SignupRequestRepository {

    @Override
    protected Username newPK(SignupRequest u) {
        return u.id();
    }

    @Override
    public Iterable<SignupRequest> pendingSignupRequests() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
