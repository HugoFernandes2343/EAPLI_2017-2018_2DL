package eapli.cafeteria.consoleapp.presentation.authz;

import eapli.framework.actions.Action;

/**
 * Menu action for user login. Created by nuno on 20/03/16.
 */
public class LoginAction implements Action {

    @Override
    public boolean execute() {
	return new LoginUI().show();
    }
}
