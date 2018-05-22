/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.movement;

import eapli.framework.actions.Action;

/**
 *
 * @author João Vieira
 */
public class ConsultMovementsAction implements Action {

    @Override
    public boolean execute() {
        return new ConsultMovementsUI().show();
    }
    
}
