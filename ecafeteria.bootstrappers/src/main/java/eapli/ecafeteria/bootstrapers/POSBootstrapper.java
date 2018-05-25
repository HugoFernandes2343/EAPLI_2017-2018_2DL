/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.pos.ClosePosService;
import eapli.ecafeteria.domain.pos.POS;
import eapli.framework.actions.Action;
import eapli.framework.domain.POSStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Norberto Sousa - 1120608
 */
public class POSBootstrapper implements Action {

    private final ClosePosService cpS = new ClosePosService();
    
    @Override
    public boolean execute() {
        POS pos1 = null;
        POS pos2 = null;
        POS pos3 = null;
        POS pos4 = null;
        
        try {
            pos1 = new POS(1);
          //  pos1.close();
            pos2 = new POS(2);
            pos2.close();
            pos3 = new POS(3);
          //  pos3.close();
            pos4 = new POS(4);
            pos4.close();
        } catch (POSStateViolationException ex) {
            System.out.println("Erro a criar pos");
        }
        
        try {
            cpS.SavePOS(pos1);
            cpS.SavePOS(pos2);
            cpS.SavePOS(pos3);
            cpS.SavePOS(pos4);
        } catch (DataConcurrencyException | DataIntegrityViolationException | NullPointerException ex) {
            System.out.println("Erro a guardar pos");
        }
        
        return true;
    }
    
}
