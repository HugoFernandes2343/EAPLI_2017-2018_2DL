package eapli.ecafeteria.application.dishes;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 * an application service to avoid code duplication.
 */
public class ListDishService {

    private DishRepository dishRepository = PersistenceContext.repositories().dishes();

    public Iterable<Dish> allDishes() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.dishRepository.findAll();
    }
}
