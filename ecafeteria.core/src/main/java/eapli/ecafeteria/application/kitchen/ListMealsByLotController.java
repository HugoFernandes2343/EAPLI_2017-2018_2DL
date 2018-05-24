package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

public class ListMealsByLotController implements Controller {
    private final LotRepository lotR = PersistenceContext.repositories().lots();

    public Iterable<Lot> findLots() {
        Iterable<Lot> list = lotR.findAll();
        return list;
    }

    public void showMealsUsed(Lot lot) {
        Iterable<Meal> result = lotR.getMealsUsed(lot);
        for(Meal m: result){
            System.out.println(m.toString());
        }
    }
}
