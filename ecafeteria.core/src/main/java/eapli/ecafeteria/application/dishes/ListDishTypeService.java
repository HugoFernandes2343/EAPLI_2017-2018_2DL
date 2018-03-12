package eapli.ecafeteria.application.dishes;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 * an application service to avoid code duplication.
 */
class ListDishTypeService {

    private final DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();

    public Iterable<DishType> allDishTypes() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.dishTypeRepository.findAll();
    }

    public Iterable<DishType> activeDishTypes() {
        AuthorizationService
                .ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.dishTypeRepository.activeDishTypes();
    }
}
