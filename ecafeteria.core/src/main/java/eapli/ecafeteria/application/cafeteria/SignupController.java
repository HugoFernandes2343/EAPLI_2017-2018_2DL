package eapli.ecafeteria.application.cafeteria;

import java.util.Calendar;

import eapli.ecafeteria.domain.cafeteria.SignupRequest;
import eapli.ecafeteria.domain.cafeteria.SignupRequestBuilder;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class SignupController implements Controller {

    private final SignupRequestRepository signupRequestRepository = PersistenceContext
            .repositories().signupRequests();

    public SignupRequest signup(final String username, final String password,
            final String firstName, final String lastName, final String email,
            String mecanographicNumber, final Calendar createdOn)
            throws DataIntegrityViolationException, DataConcurrencyException {

        // there is no need for authorisation check in this method as even
        // unauthenticated users may request a signup
        final SignupRequestBuilder signupRequestBuilder = new SignupRequestBuilder();
        signupRequestBuilder.withUsername(username).withPassword(password).withFirstName(firstName)
                .withLastName(lastName).withEmail(email).withCreatedOn(createdOn)
                .withMecanographicNumber(mecanographicNumber);

        final SignupRequest newSignupRequest = signupRequestBuilder.build();
        return this.signupRequestRepository.save(newSignupRequest);
    }

    public SignupRequest signup(final String username, final String password,
            final String firstName, final String lastName, final String email,
            String mecanographicNumber)
            throws DataIntegrityViolationException, DataConcurrencyException {

        return signup(username, password, firstName, lastName, email, mecanographicNumber,
                DateTime.now());
    }
}
