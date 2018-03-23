/**
 *
 */
package eapli.ecafeteria.app.backoffice.console.presentation.dishesviadto;

import eapli.ecafeteria.dto.DishTypeDTO;
import eapli.framework.visitor.Visitor;

/**
 * @author Paulo Gandra Sousa
 *
 */
class DishTypeDTOPrinter implements Visitor<DishTypeDTO> {

    @Override
    public void visit(DishTypeDTO visitee) {
        System.out.printf("%-10s%-30s%-4s", visitee.acronym, visitee.description,
                String.valueOf(visitee.active));
    }
}
