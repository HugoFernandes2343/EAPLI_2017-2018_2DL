/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.framework.actions.Action;

/**
 *
 * @author jsant
 */
public class PublishMenuAction implements Action {
    
    @Override
    public boolean execute(){
        return new PublishMenuUI().show();
    }
    
}
