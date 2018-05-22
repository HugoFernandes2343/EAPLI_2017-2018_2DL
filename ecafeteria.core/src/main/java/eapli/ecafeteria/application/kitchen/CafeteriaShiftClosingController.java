package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.cafeteriashift.CafeteriaShift;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.domain.pos.POSState;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.domain.reservations.ReservationState;
import eapli.ecafeteria.persistence.CafeteriaShiftRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.application.Controller;
import eapli.framework.domain.CafeteriaShiftStateViolationException;
import eapli.framework.domain.POSStateViolationException;
import eapli.framework.domain.ReservationStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import javax.persistence.NoResultException;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
public class CafeteriaShiftClosingController implements Controller {

    private final POSRepository posRP = PersistenceContext.repositories().pos();
    private final CafeteriaShiftRepository cfRP = PersistenceContext.repositories().cafeteriaShift();
    private final ReservationRepository reservRP = PersistenceContext.repositories().reservations();

    /**
     *
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     * @throws ReservationStateViolationException
     * @throws POSStateViolationException
     * @throws CafeteriaShiftStateViolationException
     */
    public void closeAllActivePOS() throws DataConcurrencyException, DataIntegrityViolationException, ReservationStateViolationException, POSStateViolationException, CafeteriaShiftStateViolationException, NoResultException {

        ArrayList<POS> list_pos = new ArrayList<>();

        list_pos = (ArrayList<POS>) posRP.findByState(POSState.OPENED);

        for (POS p : list_pos) {

            p.close();

            posRP.save(p);
        }
        
        CafeteriaShift cs = cfRP.findCafeteriaShift();

        MealType mealT = null;
        if (cs.dayTimeCheck().equalsIgnoreCase("lunch")) {
            mealT = new MealType(MealType.MealTypes.LUNCH);
        } else if (cs.dayTimeCheck().equalsIgnoreCase("dinner")) {
            mealT = new MealType(MealType.MealTypes.DINNER);
        }

        ArrayList<Reservation> list_reserv = new ArrayList<>();
        list_reserv = (ArrayList<Reservation>) reservRP.findByStateAndDate(ReservationState.BOOKED, cs.date(), mealT);
        for (Reservation r : list_reserv) {
            try {
                r.expire();
                reservRP.save(r);
            } catch (ReservationStateViolationException ex) {

            }
        }

        cs.closeShift();
        cfRP.save(cs);
    }
}
