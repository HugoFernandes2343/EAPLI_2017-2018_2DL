package eapli.ecafeteria.application.dishesviadto;

import java.util.Optional;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import eapli.ecafeteria.dto.DishDTO;
import eapli.ecafeteria.dto.DishTypeDTO;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * the controller for the use case "register new dish"
 *
 * since this controller works with DTOs it must transform from DTOs to domain
 * objects and vice versa whenever interfacing the presentation and domain
 * layers. also, some some related domain object is needed it must first be
 * retrieved from the repository prior to its use
 *
 * @author SOU03408
 */
public class RegisterDishViaDTOController implements Controller {

    private final ListDishTypeDTOService svc = new ListDishTypeDTOService();

    private final DishRepository dishRepository = PersistenceContext.repositories().dishes();
    private final DishTypeRepository dishTypeRepository = PersistenceContext.repositories()
            .dishTypes();

    public void registerDish(DishDTO dto)
            throws DataIntegrityViolationException, DataConcurrencyException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        // retrieve the dish type
        final Optional<DishType> type = dishTypeRepository.findByAcronym(dto.dishTypeAcronym);
        if (!type.isPresent()) {
            throw new IllegalArgumentException("Unknown dish type: " + dto.dishTypeAcronym);
        }

        // TODO: we are ignoring the currency and hardcoding everything is EUR
        final Dish newDish = new Dish(type.get(), Designation.valueOf(dto.name),
                new NutricionalInfo(dto.calories, dto.salt), Money.euros(dto.price));

        this.dishRepository.save(newDish);
    }

    public Iterable<DishTypeDTO> dishTypes() {
        return this.svc.activeDishTypes();
    }
}
