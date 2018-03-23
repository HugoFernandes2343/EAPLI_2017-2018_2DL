package eapli.ecafeteria.application.dishesviadto;

import eapli.ecafeteria.dto.DishDTO;
import eapli.framework.application.Controller;

/**
 * since this controller works with DTOs it must transform from DTOs to domain
 * objects and vice versa whenever interfacing the presentation and domain
 * layers. also, some some related domain object is needed it must first be
 * retrieved from the repository prior to its use
 * 
 * @author SOU03408
 */
public class ListDishDTOController implements Controller {

    private final ListDishDTOService svc = new ListDishDTOService();

    public Iterable<DishDTO> allDishes() {
        return this.svc.allDishes();
    }
}
