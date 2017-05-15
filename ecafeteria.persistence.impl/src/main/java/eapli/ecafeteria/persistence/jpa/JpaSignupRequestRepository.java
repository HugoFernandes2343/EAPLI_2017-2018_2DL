package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteria.SignupRequest;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaSignupRequestRepository extends JpaAutoTxRepository<SignupRequest, Username>
        implements SignupRequestRepository {

    public JpaSignupRequestRepository(TransactionalContext autoTx) {
        super(Application.settings().getPersistenceUnitName(), autoTx);
    }

    @Override
    public Iterable<SignupRequest> pendingSignupRequests() {
        return repo.match("e.approvalStatus=eapli.ecafeteria.domain.cafeteria.ApprovalStatus.PENDING");
    }
}
