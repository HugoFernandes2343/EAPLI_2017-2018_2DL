package eapli.ecafeteria.application.reservations;

import eapli.ecafeteria.domain.ratings.MealRating;
import eapli.ecafeteria.persistence.MealRatingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;




public class ConsultReservationRatingController implements Controller {

    private final MealRatingRepository mrp = PersistenceContext.repositories().ratings();

    public Iterable<MealRating> showRating() {
        return mrp.findAll();
    }

}
