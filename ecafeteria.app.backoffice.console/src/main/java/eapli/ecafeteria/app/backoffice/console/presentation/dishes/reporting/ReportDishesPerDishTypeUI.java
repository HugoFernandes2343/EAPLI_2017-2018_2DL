package eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting;

import eapli.ecafeteria.application.dishes.reporting.ReportDishesPerDishTypeController;
import eapli.ecafeteria.domain.dishes.reporting.DishesPerDishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 * This classes lists dish types by using the AbstractListUI from the framework.
 *
 * Created by MCN on 29/03/2016.
 */
public class ReportDishesPerDishTypeUI extends AbstractListUI<DishesPerDishType> {

    private final ReportDishesPerDishTypeController theController = new ReportDishesPerDishTypeController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected Iterable<DishesPerDishType> elements() {
        return this.theController.all();
    }

    @Override
    protected Visitor<DishesPerDishType> elementPrinter() {
        return new DishesPerDishTypePrinter();
    }

    @Override
    protected String elementName() {
        return "Dishes per dish type";
    }

    @Override
    protected String listHeader() {
        return "DISHES";
    }
}
