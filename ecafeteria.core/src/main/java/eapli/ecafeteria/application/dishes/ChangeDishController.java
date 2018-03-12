/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.dishes;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author PRP
 */
public class ChangeDishController implements Controller {

    private final ListDishService svc = new ListDishService();
    private final DishRepository dishRepository = PersistenceContext.repositories().dishes();

    public Iterable<Dish> allDishes() {
        return this.svc.allDishes();
    }

    public Dish changeDishPrice(Dish dish, Money newPrice)
            throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if (dish == null) {
            throw new IllegalArgumentException();
        }
        dish.changePriceTo(newPrice);

        return this.dishRepository.save(dish);
    }

    public Dish changeDishNutricionalInfo(Dish dish, NutricionalInfo newNutricionalInfo)
            throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if (dish == null) {
            throw new IllegalArgumentException();
        }
        dish.changeNutricionalInfoTo(newNutricionalInfo);

        return this.dishRepository.save(dish);
    }
}
