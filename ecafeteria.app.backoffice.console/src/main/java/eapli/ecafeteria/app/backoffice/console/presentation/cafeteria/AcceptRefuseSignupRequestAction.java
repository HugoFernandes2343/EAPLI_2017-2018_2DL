package eapli.ecafeteria.app.backoffice.console.presentation.cafeteria;

import eapli.framework.actions.Action;

/**
 * Created by AJS on 08/04/2016.
 */
public class AcceptRefuseSignupRequestAction implements Action {

    @Override
    public boolean execute() {
        return new AcceptRefuseSignupRequestUI().show();
    }
}
