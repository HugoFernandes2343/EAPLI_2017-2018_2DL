package eapli.ecafeteria.application.dishes;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
public class RegisterDishController implements Controller {

    private final ListDishTypeService svc = new ListDishTypeService();

    private final DishRepository dishRepository = PersistenceContext.repositories().dishes();

    public Dish registerDish(final DishType dishType, final String name, final Integer calories, final Integer salt,
            final double price) throws DataIntegrityViolationException, DataConcurrencyException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        final Dish newDish = new Dish(dishType, Designation.valueOf(name), new NutricionalInfo(calories, salt),
                Money.euros(price));

        return this.dishRepository.save(newDish);
    }

    public Iterable<DishType> dishTypes() {
        return this.svc.activeDishTypes();
    }
}
