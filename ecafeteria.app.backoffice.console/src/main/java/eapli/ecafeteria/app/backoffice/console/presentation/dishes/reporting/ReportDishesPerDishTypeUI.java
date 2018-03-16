package eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting;

import eapli.ecafeteria.application.dishes.reporting.DishReportingController;
import eapli.ecafeteria.domain.dishes.reporting.DishesPerDishType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 * This classes creates a report on the number of dishes per dish types by using
 * the AbstractListUI from the framework.
 *
 * @author PAG
 */
public class ReportDishesPerDishTypeUI extends AbstractListUI<DishesPerDishType> {

    private final DishReportingController theController = new DishReportingController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected Iterable<DishesPerDishType> elements() {
        return this.theController.reportDishesPerDishType();
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
        return "DISHES PER DISH TYPE";
    }
}
