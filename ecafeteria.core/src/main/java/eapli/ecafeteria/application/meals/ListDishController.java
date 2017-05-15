package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.Dish;
import eapli.framework.application.Controller;

/**
 * Created on 29/03/2016.
 */
public class ListDishController implements Controller {

    private ListDishService svc = new ListDishService();

    public Iterable<Dish> allDishes() {
        return this.svc.allDishes();
    }
}
