/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Money;
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

    public void changeDishPrice(Dish dish, Money newPrice)
	    throws DataConcurrencyException, DataIntegrityViolationException {
	Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
	if (dish == null) {
	    throw new IllegalArgumentException();
	}
	dish.changePriceTo(newPrice);

	Dish ret = this.dishRepository.save(dish);
    }

    public void changeDishNutricionalInfo(Dish dish, NutricionalInfo newNutricionalInfo)
	    throws DataConcurrencyException, DataIntegrityViolationException {
	Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
	if (dish == null) {
	    throw new IllegalArgumentException();
	}
	dish.changeNutricionalInfoTo(newNutricionalInfo);

	Dish ret = this.dishRepository.save(dish);
    }
}
