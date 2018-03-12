/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.cafeteriauser.AcceptRefuseSignupRequestController;
import eapli.ecafeteria.application.cafeteriauser.SignupController;
import eapli.ecafeteria.domain.cafeteriauser.SignupRequest;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Sousa
 */
public class CafeteriaUserBootstrapper implements Action {

    @Override
    public boolean execute() {
        signupAndApprove("900330", "Password1", "John", "Smith", "john@smith.com", "900330");
        signupAndApprove("900331", "Password1", "Mary", "Smith", "mary@smith.com", "900331");

        return true;
    }

    /**
     *
     */
    private SignupRequest signupAndApprove(final String username, final String password, final String firstName,
            final String lastName, final String email, String mecanographicNumber) {
        final SignupController signupController = new SignupController();
        final AcceptRefuseSignupRequestController acceptController = new AcceptRefuseSignupRequestController();
        SignupRequest request = null;
        try {
            request = signupController.signup(username, password, firstName, lastName, email,
                    mecanographicNumber);
            acceptController.acceptSignupRequest(request);
        } catch (final DataConcurrencyException | DataIntegrityViolationException e) {
            // assume it just a question of trying to insert duplicate record
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: Exception during bootstrapping: assuming existing record.");
        }
        return request;
    }
}
