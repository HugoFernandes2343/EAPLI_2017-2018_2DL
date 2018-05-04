/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eapli.ecafeteria.app.user.console.presentation.consultMenu;

import eapli.framework.actions.Action;
/**
 *
 * @author Joel Costa <1130270@isep.ipp.pt>
 */
public class ConsultMenuAction implements Action {

    @Override
    public boolean execute() {
        return new ConsultMenuUI().show();
    }

}
