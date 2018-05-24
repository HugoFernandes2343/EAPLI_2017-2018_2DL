package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

public class ListMealsByLotController implements Controller {
    private final MealRepository mealR = PersistenceContext.repositories().meals();
    private final LotRepository lotR = PersistenceContext.repositories().lots();


    public Iterable<Lot> findLots() {
        Iterable<Lot> list = lotR.findAll();
        return list;
    }

    public void showMealsUsed(Lot lot) {
        Iterable<Meal> result = mealR.getMealsUsed(lot);
        for(Object o: result){
            Meal m = (Meal) o;
            System.out.println(m.toString());
        }
    }
}
