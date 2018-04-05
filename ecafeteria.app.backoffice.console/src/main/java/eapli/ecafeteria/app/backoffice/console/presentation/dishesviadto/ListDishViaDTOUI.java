package eapli.ecafeteria.app.backoffice.console.presentation.dishesviadto;

import eapli.ecafeteria.application.dishesviadto.ListDishDTOController;
import eapli.ecafeteria.dto.DishDTO;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 * This classes lists dish types by using the AbstractListUI from the framework.
 *
 * Created by MCN on 29/03/2016.
 */
public class ListDishViaDTOUI extends AbstractListUI<DishDTO> {

    private final ListDishDTOController theController = new ListDishDTOController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected Iterable<DishDTO> elements() {
        return this.theController.allDishes();
    }

    @Override
    protected Visitor<DishDTO> elementPrinter() {
        return new DishDTOPrinter();
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
