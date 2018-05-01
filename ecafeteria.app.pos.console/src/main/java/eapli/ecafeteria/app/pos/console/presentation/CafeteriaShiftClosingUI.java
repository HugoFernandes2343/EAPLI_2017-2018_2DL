package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.kitchen.CafeteriaShiftClosingController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
public class CafeteriaShiftClosingUI extends AbstractUI {

    private final CafeteriaShiftClosingController controller = new CafeteriaShiftClosingController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        
        int win = JOptionPane.showConfirmDialog(null, "Close cafeteria shift?", "eCafeteria", 0);

        if (win == 0) {
            try {
                controller.closeAllActivePOS();
            } catch (DataConcurrencyException ex) {
                Logger.getLogger(CafeteriaShiftClosingUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataIntegrityViolationException ex) {
                Logger.getLogger(CafeteriaShiftClosingUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "Shift Closed!");
        } else {
            JOptionPane.showMessageDialog(null, "Operation Canceled!");
        }
        return true;
    }

    @Override
    public String headline() {
        return "Close Cafeteria Shift";
    }

}
