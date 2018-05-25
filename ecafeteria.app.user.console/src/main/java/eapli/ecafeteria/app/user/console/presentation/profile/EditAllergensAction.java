/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.profile;

import eapli.framework.actions.Action;

/**
 *
 * @author Rodrigo Soares <1140420@isep.ipp.pt>
 */
public class EditAllergensAction implements Action{
    @Override
    public boolean execute() {
        return new EditAllergensUI().show();
    }
}
