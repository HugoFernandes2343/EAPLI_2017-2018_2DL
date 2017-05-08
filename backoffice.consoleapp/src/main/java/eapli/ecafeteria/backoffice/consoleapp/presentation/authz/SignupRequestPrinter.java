/**
 *
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.authz;

import eapli.ecafeteria.domain.cafeteria.SignupRequest;
import eapli.framework.visitor.Visitor;

/**
 * Created by AJS on 08/04/2016.
 *
 */
class SignupRequestPrinter implements Visitor<SignupRequest> {

    @Override
    public void visit(SignupRequest visitee) {
	System.out.printf("%-10s%-20s%-20s%-10s\n", visitee.id(), visitee.name(), visitee.organicUnit().name(),
		visitee.mecanographicNumber());
    }

    @Override
    public void beforeVisiting(SignupRequest visitee) {
	// nothing to do
    }

    @Override
    public void afterVisiting(SignupRequest visitee) {
	// nothing to do
    }
}
