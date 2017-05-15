/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author mcn
 */
public class ActivateDeactivateDishController implements Controller {

    private final ListDishService svc = new ListDishService();
    private final DishRepository dishRepository = PersistenceContext.repositories().dishes();

    public Iterable<Dish> allDishes() {
        return this.svc.allDishes();
    }

    public void changeDishState(Dish dish) throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if (dish == null) {
            throw new IllegalArgumentException();
        }
        dish.toogleState();

        Dish ret = this.dishRepository.save(dish);
    }
}
