package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.application.Controller;

/**
 * Created on 29/03/2016.
 */
public class ListDishTypeController implements Controller {

    private final ListDishTypeService svc = new ListDishTypeService();

    public Iterable<DishType> listDishTypes() {
        return this.svc.activeDishTypes();
    }
}
