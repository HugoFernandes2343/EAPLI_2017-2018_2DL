package eapli.ecafeteria.application.dishesviadto;

import java.util.ArrayList;
import java.util.List;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.dto.DishDTO;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 * an application service to avoid code duplication.
 *
 * since this service works with DTOs it must transform from DTOs to domain
 * objects and vice versa whenever interfacing the presentation and domain
 * layers.
 *
 * @author SOU03408
 */
public class ListDishDTOService {

    private final DishRepository dishRepository = PersistenceContext.repositories().dishes();

    public Iterable<DishDTO> allDishes() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        final Iterable<Dish> dishes = this.dishRepository.findAll();

        // transform for the presentation layer
        final List<DishDTO> ret = new ArrayList<>();
        dishes.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }
}
