/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.app.common.console.presentation.authz;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.actions.Action;
import eapli.framework.actions.CompoundAction;

/**
 *
 * @author Paulo Gandra Sousa
 */
public class ValidateLoginAction extends CompoundAction {

    private final ActionRight expectedActionRight;

    public ValidateLoginAction(ActionRight permission, Action next) {
        super(next);
        expectedActionRight = permission;
    }

    @Override
    public boolean execute() {
        LoginUI loginUI = new LoginUI();
        if (loginUI.show()) {
            if (AuthorizationService.session().authenticatedUser().isAuthorizedTo(expectedActionRight)) {
                return next();
            } else {
                System.out.println("you are not authorized for using this app");
            }
        }
        return false;
    }
}
