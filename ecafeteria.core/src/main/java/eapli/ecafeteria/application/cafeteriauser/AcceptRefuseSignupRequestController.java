/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.SystemUserBuilder;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUserBuilder;
import eapli.ecafeteria.domain.cafeteriauser.SignupRequest;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 *
 * Created by AJS on 08/04/2016.
 *
 * @FIXME this controller has lots of logic that should be moved to a domain
 * service
 *
 * @TODO there is some code duplication to create and add the system user
 *
 * @TODO following the guideline that a controller should only change one
 * Aggregate, we shouldn't be changing all these entities here, but should
 * instead use asynchronous events. However in this case we will take advantage
 * of TransactionalContext
 */
public class AcceptRefuseSignupRequestController implements Controller {

    private final TransactionalContext TxCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final UserRepository userRepository = PersistenceContext.repositories().users(TxCtx);
    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories()
            .cafeteriaUsers(TxCtx);
    private final SignupRequestRepository signupRequestsRepository = PersistenceContext.repositories()
            .signupRequests(TxCtx);

    public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest)
            throws DataIntegrityViolationException, DataConcurrencyException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        if (theSignupRequest == null) {
            throw new IllegalArgumentException();
        }

        try {// explicitly begin a transaction
            TxCtx.beginTransaction();
        } catch (NullPointerException e) {
        }
        final SystemUser newUser = createSystemUserForCafeteriaUser(theSignupRequest);
        createCafeteriaUser(theSignupRequest, newUser);
        theSignupRequest = acceptTheSignupRequest(theSignupRequest);

        try {// explicitly begin a transaction

            // explicitly commit the transaction
            TxCtx.commit();
        } catch (NullPointerException e) {
        }
        return theSignupRequest;
    }

    private SignupRequest acceptTheSignupRequest(SignupRequest theSignupRequest)
            throws DataConcurrencyException, DataIntegrityViolationException {
        theSignupRequest.accept();
        theSignupRequest = this.signupRequestsRepository.save(theSignupRequest);
        return theSignupRequest;
    }

    private void createCafeteriaUser(SignupRequest theSignupRequest, SystemUser newUser)
            throws DataConcurrencyException, DataIntegrityViolationException {
        final CafeteriaUserBuilder cafeteriaUserBuilder = new CafeteriaUserBuilder();
        cafeteriaUserBuilder.withMecanographicNumber(theSignupRequest.mecanographicNumber())
                .withSystemUser(newUser);
        this.cafeteriaUserRepository.save(cafeteriaUserBuilder.build());
    }

    //
    // add system user
    //
    private SystemUser createSystemUserForCafeteriaUser(SignupRequest theSignupRequest)
            throws DataConcurrencyException, DataIntegrityViolationException {

        final SystemUserBuilder userBuilder = SystemUserBuilder.forSignupRequest(theSignupRequest);
        // TODO error checking if the username is already in the persistence
        // store
        return this.userRepository.save(userBuilder.build());
    }

    public SignupRequest refuseSignupRequest(SignupRequest theSignupRequest)
            throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        if (theSignupRequest == null) {
            throw new IllegalArgumentException();
        }

        // explicitly begin a transaction
        TxCtx.beginTransaction();

        theSignupRequest.refuse();
        theSignupRequest = this.signupRequestsRepository.save(theSignupRequest);

        // explicitly commit the transaction
        TxCtx.commit();

        return theSignupRequest;
    }

    /**
     *
     * @return
     */
    public Iterable<SignupRequest> listPendingSignupRequests() {
        return this.signupRequestsRepository.pendingSignupRequests();
    }
}
