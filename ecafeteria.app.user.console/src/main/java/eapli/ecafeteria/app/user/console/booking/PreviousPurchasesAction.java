/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.booking;

import eapli.framework.actions.Action;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class PreviousPurchasesAction implements Action {

    @Override
    public boolean execute() {
        return new PreviousPurchasesUI().show();
    }
    
}
