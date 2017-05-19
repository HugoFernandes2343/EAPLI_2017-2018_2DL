/**
 *
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.Dish;
import eapli.framework.visitor.Visitor;

/**
 * @author ajs 13/04/2016
 *
 */
class DishPrinter implements Visitor<Dish> {

    @Override
    public void visit(Dish visitee) {
        System.out.printf("%-30s%-25s%-10s%-4s\n", visitee.name(), visitee.dishType().description(),
                visitee.currentPrice(), String.valueOf(visitee.isActive()));
    }
}
