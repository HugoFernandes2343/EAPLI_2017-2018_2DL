package eapli.ecafeteria.app.user.console.booking;

import eapli.framework.actions.Action;

/**
 *
 */
public class ConsultReservationRatingAction implements Action {

    @Override
    public boolean execute() {
        return new ConsultReservationRatingUI().doShow();
    }
}
