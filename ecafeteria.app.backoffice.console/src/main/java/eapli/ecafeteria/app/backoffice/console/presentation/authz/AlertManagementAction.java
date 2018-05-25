/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.authz;

import eapli.framework.actions.Action;

/**
 *
 * @author 1150425
 */
public class AlertManagementAction implements Action{
    
    @Override
    public boolean execute() {
        return new KitchenAlertManagementUI().show();
    }
    
}
