/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.allergens;

import eapli.ecafeteria.application.dishes.ListDishService;
import eapli.ecafeteria.domain.allergens.Allergen;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.EnumSet;
import java.util.List;

/**
 *
 * @author 1150425
 */
public class DishAllergensListController {

    private final ListDishService svc = new ListDishService();
    private final DishRepository repository = PersistenceContext.repositories().dishes();

    private final EnumSet<Allergen> allergenList = EnumSet.allOf(Allergen.class);

    public Iterable<Dish> allDishes() {
        return this.svc.allDishes();
    }

    public Iterable<Allergen> allAllergens() {
        return this.allergenList;
    }

    /**
     * @param dish
     * @param allergen Method to add an Allergen to a dish. If the dish has no
     * allergen the method creates a dish
     * @return 
     * @throws eapli.framework.persistence.DataIntegrityViolationException
     * @throws eapli.framework.persistence.DataConcurrencyException
     */
    public boolean addAllergenToDish(Dish dish, Allergen allergen) throws DataIntegrityViolationException, DataConcurrencyException {
        if (dish.allergenList() == null || dish.allergenList().isEmpty()) {
            dish.addAllergen(allergen);
        } else {
            dish.addAllergen(allergen);
        }
        return true;
    }

    public List<Allergen> dishAllergens(Dish selectedDish) {
        return selectedDish.allergenList();
    }

    public boolean removeAllergen(Allergen selectedAllergen, Dish selectedDish) {
        return selectedDish.removeAllergen(selectedAllergen);
    }

    public Dish save(Dish dish) throws DataConcurrencyException, DataIntegrityViolationException {
        return repository.save(dish);
    }

}
