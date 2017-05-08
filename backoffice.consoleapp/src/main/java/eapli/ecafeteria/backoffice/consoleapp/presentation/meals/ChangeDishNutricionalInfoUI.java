/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.ChangeDishController;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.ecafeteria.domain.meals.NutricionalInfo;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PRP 29.mar.2017
 */
class ChangeDishNutricionalInfoUI extends AbstractUI {

    private final ChangeDishController theController = new ChangeDishController();

    protected Controller controller() {
	return this.theController;
    }

    @Override
    protected boolean doShow() {

	final Iterable<Dish> allDishes = this.theController.allDishes();
	if (!allDishes.iterator().hasNext()) {
	    System.out.println("There are no registered Dishes");
	} else {
	    final SelectWidget<Dish> selector = new SelectWidget<>("Dishes:", allDishes, new DishPrinter());
	    selector.show();
	    final Dish selectedDish = selector.selectedElement();
	    System.out.println("Current  nutricional information:" + selectedDish.nutricionalInfo().toString());
	    try {
		NutricionalInfoDataWidget newNutricionalData = new NutricionalInfoDataWidget();
		newNutricionalData.show();
		this.theController.changeDishNutricionalInfo(selectedDish,
			new NutricionalInfo(newNutricionalData.calories(), newNutricionalData.salt()));
	    } catch (DataConcurrencyException ex) {
		System.out
			.println("It is not possible to change the dish state because it was changed by another user");
	    } catch (DataIntegrityViolationException ex) {
		Logger.getLogger(ChangeDishNutricionalInfoUI.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	return true;
    }

    @Override
    public String headline() {
	return "Change Dish Nutricional Info";
    }
}
