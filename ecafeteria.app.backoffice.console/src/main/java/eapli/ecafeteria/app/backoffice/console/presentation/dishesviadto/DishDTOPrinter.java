/**
 *
 */
package eapli.ecafeteria.app.backoffice.console.presentation.dishesviadto;

import eapli.ecafeteria.dto.DishDTO;
import eapli.framework.visitor.Visitor;

/**
 * @author SOU03408
 */
@SuppressWarnings("squid:S106")
public class DishDTOPrinter implements Visitor<DishDTO> {

    @Override
    public void visit(DishDTO visitee) {
        System.out.printf("%-30s%-25s%-10s%-4s", visitee.name, visitee.dishTypeDescription,
                visitee.price, String.valueOf(visitee.active));
    }
}
