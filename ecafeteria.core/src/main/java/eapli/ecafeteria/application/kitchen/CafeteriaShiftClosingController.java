package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.pos.ClosePosService;
import eapli.ecafeteria.domain.cafeteriashift.CafeteriaShift;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.domain.pos.POSState;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.domain.reservations.ReservationState;
import eapli.ecafeteria.persistence.CafeteriaShiftRepository;
import eapli.ecafeteria.persistence.MealPlanItemRepository;
import eapli.ecafeteria.persistence.MealRepository;
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
    private final MealRepository mRP = PersistenceContext.repositories().meals();
    private final MealPlanItemRepository mpiRP = PersistenceContext.repositories().mealPlanItemRepository();
    private final ClosePosService cpS = new ClosePosService();

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

            cpS.CloseAndSavePOS(p.code(), list_pos);

            System.out.println("\n We closed pos number: " + p.code());
        }

        CafeteriaShift cs = cfRP.findCafeteriaShift();

        MealType mealT = null;
        if (cs.dayTimeCheck().equalsIgnoreCase("lunch")) {
            mealT = new MealType(MealType.MealTypes.LUNCH);
             System.out.println("It's lunch time.");
        } else if (cs.dayTimeCheck().equalsIgnoreCase("dinner")) {
            mealT = new MealType(MealType.MealTypes.DINNER);
             System.out.println("It's dinner time.");
        }

        ArrayList<Meal> list_meals = new ArrayList<>();

        list_meals = (ArrayList<Meal>) mRP.findMealsByDateAndMealType(cs.date(), mealT);

        System.out.println("\n Number of different meals for this lunch " + list_meals.size());
        
        for (Meal m : list_meals) {
            ArrayList<Reservation> list_reserv = new ArrayList<>();
            list_reserv = (ArrayList<Reservation>) reservRP.findByStateAndMeal(ReservationState.BOOKED, m);

            System.out.println( m.toString() );

            for (Reservation r : list_reserv) {
                try {
                    r.expire();
                    reservRP.save(r);

                    System.out.println("\n This is the number " + r.id() +" reservation. ");

                } catch (ReservationStateViolationException ex) {
                }
            }
            MealPlanItem mp = mpiRP.findByMeal(m);

            System.out.println("\n Number of expired meals: " + list_reserv.size());

            mp.calculateWastedMeals(list_reserv.size());

            System.out.println("\n Meal plan item: " + mp.toStringValuesOnly());

            mpiRP.save(mp);
        }

        cs.closeShift();
        SaveCafeteriaShift(cs);
    }

    public CafeteriaShift SaveCafeteriaShift(CafeteriaShift cs) throws DataConcurrencyException, DataIntegrityViolationException {
        return cfRP.save(cs);
    }
}
