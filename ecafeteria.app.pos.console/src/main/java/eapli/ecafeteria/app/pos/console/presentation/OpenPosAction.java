/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.framework.actions.Action;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
public class OpenPosAction implements Action {
    
    @Override
    public boolean execute() {
        return new OpenPosUI().show();
    } 
    
}
