package eapli.ecafeteria.app.user.console.presentation.authz;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import java.util.logging.Level;
import java.util.logging.Logger;

import eapli.ecafeteria.application.cafeteriauser.SignupController;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.Console;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class SignupRequestUI extends CafeteriaUserBaseUI {

    private final SignupController theController = new SignupController();


    @Override
    protected boolean doShow() {
        final UserDataWidget userData = new UserDataWidget();

        userData.show();

        final String mecanographicNumber = Console.readLine("Mecanographic Number");

        try {
            this.theController.signup(userData.username(), userData.password(),
                    userData.firstName(), userData.lastName(), userData.email(),
                    mecanographicNumber);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(SignupRequestUI.class.getName()).log(Level.SEVERE, null, e);
        }

        return true;
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

}
