package eapli.ecafeteria.app.backoffice.console.presentation.dishesviadto;

import eapli.ecafeteria.app.backoffice.console.presentation.dishes.NutricionalInfoDataWidget;
import eapli.ecafeteria.application.dishesviadto.RegisterDishViaDTOController;
import eapli.ecafeteria.dto.DishDTO;
import eapli.ecafeteria.dto.DishTypeDTO;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

/**
 *
 * @author SOU03408
 */
public class RegisterDishViaDTOUI extends AbstractUI {

    private final RegisterDishViaDTOController theController = new RegisterDishViaDTOController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    @SuppressWarnings("squid:S106")
    protected boolean doShow() {
        final Iterable<DishTypeDTO> dishTypes = this.theController.dishTypes();

        final SelectWidget<DishTypeDTO> selector = new SelectWidget<>("Dish types:", dishTypes,
                new DishTypeDTOPrinter());
        selector.show();
        final DishTypeDTO theDishType = selector.selectedElement();

        final String name = Console.readLine("Name");

        final NutricionalInfoDataWidget nutricionalData = new NutricionalInfoDataWidget();

        nutricionalData.show();

        final double price = Console.readDouble("Price");

        try {
            final DishDTO dish = new DishDTO(theDishType.acronym, theDishType.description, name,
                    nutricionalData.calories(), nutricionalData.salt(), price, "EUR", true);
            this.theController.registerDish(dish);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("You tried to enter a dish which already exists in the database.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Dish";
    }
}
