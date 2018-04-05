/**
 *
 */
package eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting;

import eapli.ecafeteria.reporting.dishes.DishesPerDishType;
import eapli.framework.visitor.Visitor;

/**
 * @author ajs 13/04/2016
 *
 */
class DishesPerDishTypePrinter implements Visitor<DishesPerDishType> {

    @Override
    public void visit(DishesPerDishType visitee) {
        System.out.printf("%-30s%-10s", visitee.dishType, visitee.quantityOfDishes
        );
    }
}
