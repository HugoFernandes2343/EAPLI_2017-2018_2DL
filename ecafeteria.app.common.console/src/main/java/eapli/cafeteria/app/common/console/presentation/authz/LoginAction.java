package eapli.cafeteria.app.common.console.presentation.authz;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.actions.Action;

/**
 * Menu action for user login. Created by nuno on 20/03/16.
 */
public class LoginAction implements Action {

    private ActionRight onlyWithThis;

    public LoginAction() {

    }

    /**
     *
     * @param onlyWithThis only if the user has this specific action right will
     * be allowed to login
     */
    public LoginAction(ActionRight onlyWithThis) {
        this.onlyWithThis = onlyWithThis;
    }

    @Override
    public boolean execute() {
        return new LoginUI(onlyWithThis).show();
    }
}
