/**
 *
 */
package eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting;

import eapli.ecafeteria.domain.dishes.reporting.DishesPerCaloricCategory;
import eapli.framework.visitor.Visitor;

/**
 * @author PAG
 *
 */
@SuppressWarnings("squid:S106")
class DishesPerCaloricCategoryPrinter implements Visitor<DishesPerCaloricCategory> {

    @Override
    public void visit(final DishesPerCaloricCategory visitee) {
	System.out.printf("%-30s%-10s", visitee.caloricCategory, visitee.quantityOfDishes);
    }
}
