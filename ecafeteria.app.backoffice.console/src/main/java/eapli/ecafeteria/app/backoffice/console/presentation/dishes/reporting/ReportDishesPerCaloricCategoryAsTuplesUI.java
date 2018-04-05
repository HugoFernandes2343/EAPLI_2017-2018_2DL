package eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting;

import eapli.ecafeteria.application.reporting.dishes.DishReportingController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 * This classes lists the number of dishes per dish type by using the
 * AbstractListUI from the framework.
 *
 * @author Paulo Gandra de Sousa
 */
public class ReportDishesPerCaloricCategoryAsTuplesUI extends AbstractListUI<Object[]> {

    private final DishReportingController theController = new DishReportingController();

    protected Controller controller() {
        return theController;
    }

    @Override
    protected Iterable<Object[]> elements() {
        return theController.reportDishesPerCaloricCategoryAsTuples();
    }

    @Override
    protected Visitor<Object[]> elementPrinter() {
        return new TuplePrinter();
    }

    @Override
    protected String elementName() {
        return "Dishes per Caloric Category";
    }

    @Override
    protected String listHeader() {
        return "DISHES PER CALORIC CATEGORY (tuples)";
    }
}
