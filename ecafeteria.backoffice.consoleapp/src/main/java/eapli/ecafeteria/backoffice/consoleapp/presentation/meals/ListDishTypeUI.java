package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.ListDishTypeController;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 * This classes lists dish types by using the AbstractListUI from the framework.
 *
 * Created by MCN on 29/03/2016.
 */
public class ListDishTypeUI extends AbstractListUI<DishType> {

    private final ListDishTypeController theController = new ListDishTypeController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected Iterable<DishType> listOfElements() {
        return this.theController.listDishTypes();
    }

    @Override
    protected Visitor<DishType> elementPrinter() {
        return new DishTypePrinter();
    }

    @Override
    protected String elementName() {
        return "Dish Type";
    }
}
