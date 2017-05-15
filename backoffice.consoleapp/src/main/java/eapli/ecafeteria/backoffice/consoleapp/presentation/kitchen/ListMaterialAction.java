/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen;

import eapli.framework.actions.Action;

/**
 *
 * @author mcn
 */
public class ListMaterialAction implements Action {

    @Override
    public boolean execute() {
        return new ListMaterialUI().show();
    }
}
