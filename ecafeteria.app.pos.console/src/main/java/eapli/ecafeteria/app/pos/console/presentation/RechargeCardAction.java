package eapli.ecafeteria.app.pos.console.presentation;

import eapli.framework.actions.Action;

/**
 *
 * @author 1161110 & 1161213
 */
public class RechargeCardAction implements Action {

    @Override
    public boolean execute() {
        return new RechargeCardUI().doShow();
    }
    
}
