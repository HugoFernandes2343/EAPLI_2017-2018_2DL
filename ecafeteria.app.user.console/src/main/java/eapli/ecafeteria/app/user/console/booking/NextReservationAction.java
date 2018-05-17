package eapli.ecafeteria.app.user.console.booking;

import eapli.framework.actions.Action;

/**
 * @author <1160777@isep.ipp.pt>Marco Carneiro</1160777@isep.ipp.pt>
 */
public class NextReservationAction implements Action {

    @Override
    public boolean execute() {
        return new ConsultNextReservationUI().show();
    }
}
