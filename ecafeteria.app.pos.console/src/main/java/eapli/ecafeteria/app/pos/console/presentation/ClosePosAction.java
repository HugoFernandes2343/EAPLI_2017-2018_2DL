
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.framework.actions.Action;

public class ClosePosAction implements Action {
    
    @Override
    public boolean execute() {
        return new ClosePosUI().show();
    }
}
