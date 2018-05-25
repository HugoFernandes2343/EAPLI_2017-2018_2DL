package eapli.ecafeteria.app.user.console.booking;

import eapli.ecafeteria.application.reservations.ConsultReservationRatingController;
import eapli.ecafeteria.domain.ratings.MealRating;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class ConsultReservationRatingUI extends AbstractUI {

    private final ConsultReservationRatingController theController = new ConsultReservationRatingController();
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        List<MealRating> ratL = (List) theController.showRating();
        for(MealRating mr : ratL){
            mr.toString();
        }
        return true;
    }

    @Override
    public String headline() {
        return "Consult Reservation's Rating";
    }
}
