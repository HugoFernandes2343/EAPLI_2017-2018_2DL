/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.framework.actions.Action;

/**
 *
 * @author João Santiago <1160696@isep.ipp.pt>
 */
public class ConsultMealRatingAction implements Action{

    @Override
    public boolean execute() {
        return new ConsultMealRatingUI().show();
    }
    
    
}
