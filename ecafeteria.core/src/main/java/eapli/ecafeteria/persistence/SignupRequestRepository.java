package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.SignupRequest;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface SignupRequestRepository extends DataRepository<SignupRequest, Username> {

	Iterable<SignupRequest> pendingSignupRequests();
}
