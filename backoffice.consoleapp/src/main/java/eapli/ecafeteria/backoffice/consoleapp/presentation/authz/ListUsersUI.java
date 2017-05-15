/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.authz;

import eapli.ecafeteria.application.authz.ListUsersController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

/**
 * TODO avoid duplicate code by using AbstractListUI from the framework
 *
 * @author losa
 */
public class ListUsersUI extends AbstractUI {

    private final ListUsersController theController = new ListUsersController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<SystemUser> iterable = this.theController.allUsers();
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered User");
        } else {
            // TODO use a widget, see, e.g., ListDishTypesUI
            for (final SystemUser user : iterable) {
                // TODO display other attributes
                System.out.printf("%10s %30s %30s\n", user.username(), user.name().firstName(), user.name().lastName());
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "List Users";
    }
}
