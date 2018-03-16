package eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting;

import eapli.ecafeteria.app.backoffice.console.presentation.dishes.DishPrinter;
import eapli.ecafeteria.application.dishes.reporting.DishReportingController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 * This classes lists the number of dishes per dish type by using the
 * AbstractListUI from the framework.
 *
 * @author Paulo Gandra de Sousa
 */
public class ReportHighCaloriesDishesUI extends AbstractListUI<Dish> {

    private final DishReportingController theController = new DishReportingController();

    protected Controller controller() {
        return theController;
    }

    @Override
    protected Iterable<Dish> elements() {
        return theController.reportHighCaloriesDishes();
    }

    @Override
    protected Visitor<Dish> elementPrinter() {
        return new DishPrinter();
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
