/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.framework.actions.Action;

/**
 *
 * @author Nuno
 */
public class ChangeDishTypeAction implements Action {

    @Override
    public boolean execute() {
        return new ChangeDishTypeUI().show();
    }
}
