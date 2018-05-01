
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.framework.actions.Action;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
public class CafeteriaShiftClosingAction implements Action {

    @Override
    public boolean execute() {
        return new CafeteriaShiftClosingUI().show();
    }
    
}
