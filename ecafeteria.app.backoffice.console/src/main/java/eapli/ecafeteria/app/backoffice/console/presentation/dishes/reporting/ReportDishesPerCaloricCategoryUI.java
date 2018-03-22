package eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting;

import eapli.ecafeteria.application.dishes.reporting.DishReportingController;
import eapli.ecafeteria.domain.dishes.reporting.DishesPerCaloricCategory;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 * This classes lists the number of dishes per dish type by using the
 * AbstractListUI from the framework.
 *
 * @author Paulo Gandra de Sousa
 */
public class ReportDishesPerCaloricCategoryUI extends AbstractListUI<DishesPerCaloricCategory> {

    private final DishReportingController theController = new DishReportingController();

    protected Controller controller() {
        return theController;
    }

    @Override
    protected Iterable<DishesPerCaloricCategory> elements() {
        return theController.reportDishesPerCaloricCategory();
    }

    @Override
    protected Visitor<DishesPerCaloricCategory> elementPrinter() {
        return new DishesPerCaloricCategoryPrinter();
    }

    @Override
    protected String elementName() {
        return "Dishes per Caloric Category";
    }

    @Override
    protected String listHeader() {
        return "DISHES PER CALORIC CATEGORY";
    }
}
