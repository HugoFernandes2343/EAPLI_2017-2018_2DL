/**
 *
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.domain.meals.DishType;
import eapli.framework.visitor.Visitor;

/**
 * @author Paulo Gandra Sousa
 *
 */
class DishTypePrinter implements Visitor<DishType> {

    @Override
    public void visit(DishType visitee) {
        System.out.printf("%-10s%-30s%-4s\n", visitee.id(), visitee.description(), String.valueOf(visitee.isActive()));
    }

    @Override
    public void beforeVisiting(DishType visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(DishType visitee) {
        // nothing to do
    }
}
