package eapli.ecafeteria.app.backoffice.console.presentation.menu;

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
