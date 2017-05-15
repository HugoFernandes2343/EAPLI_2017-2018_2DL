package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 * an application service to avoid code duplication.
 */
public class ListDishService {

    private DishRepository dishRepository = PersistenceContext.repositories().dishes();

    public Iterable<Dish> allDishes() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.dishRepository.findAll();
    }
}
