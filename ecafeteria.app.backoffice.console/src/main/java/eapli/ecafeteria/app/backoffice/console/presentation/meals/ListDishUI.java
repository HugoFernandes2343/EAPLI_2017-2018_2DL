package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.application.dishes.ListDishController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 * This classes lists dish types by using the AbstractListUI from the framework.
 *
 * Created by MCN on 29/03/2016.
 */
public class ListDishUI extends AbstractListUI<Dish> {

	private final ListDishController theController = new ListDishController();

	protected Controller controller() {
		return this.theController;
	}

	@Override
	protected Iterable<Dish> elements() {
		return this.theController.allDishes();
	}

	@Override
	protected Visitor<Dish> elementPrinter() {
		return new DishPrinter();
	}

	@Override
	protected String elementName() {
		return "Dish";
	}

	@Override
	protected String listHeader() {
		return "DISHES";
	}
}
