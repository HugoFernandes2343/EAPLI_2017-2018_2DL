package eapli.ecafeteria.application.dishesviadto;

import java.util.ArrayList;
import java.util.List;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.dto.DishTypeDTO;
import eapli.ecafeteria.persistence.DishTypeRepository;
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
class ListDishTypeDTOService {

    private final DishTypeRepository dishTypeRepository = PersistenceContext.repositories()
            .dishTypes();

    public Iterable<DishTypeDTO> allDishTypes() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        final Iterable<DishType> types = this.dishTypeRepository.findAll();
        return transformToDTO(types);
    }

    private Iterable<DishTypeDTO> transformToDTO(final Iterable<DishType> types) {
        // transform for the presentation layer
        final List<DishTypeDTO> ret = new ArrayList<>();
        types.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }

    public Iterable<DishTypeDTO> activeDishTypes() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return transformToDTO(this.dishTypeRepository.activeDishTypes());
    }
}
