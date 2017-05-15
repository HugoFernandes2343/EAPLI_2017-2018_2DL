package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 * an application service to avoid code duplication.
 */
class ListDishTypeService {

    private final DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();

    public Iterable<DishType> allDishTypes() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.dishTypeRepository.findAll();
    }

    public Iterable<DishType> activeDishTypes() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.dishTypeRepository.activeDishTypes();
    }
}
