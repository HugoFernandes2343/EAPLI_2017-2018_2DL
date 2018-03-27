/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.reporting.dishes;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.persistence.DishReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.reporting.dishes.DishesPerCaloricCategory;
import eapli.ecafeteria.reporting.dishes.DishesPerDishType;
import eapli.framework.application.Controller;

/**
 * reporting is a separate concern from business logic, as such we are placing
 * reporting features in a separate package structure.
 *
 * @author pgsou_000
 */
public class DishReportingController implements Controller {

    private final DishReportingRepository repo = PersistenceContext.repositories().dishReporting();

    /**
     * reports the number of dishes per dish type
     *
     * in this case we are using a custom reporting DTO
     *
     * @return
     */
    public Iterable<DishesPerDishType> reportDishesPerDishType() {
        return repo.dishesPerDishType();
    }

    /**
     * reports the dishes that have a high calorie count
     *
     * "high calorie" is defined in the application properties
     *
     * @return
     */
    public Iterable<Dish> reportHighCaloriesDishes() {
        return repo.reportHighCaloriesDishes(Application.settings().getHighCaloriesDishLimit());
    }

    /**
     * reports the number of dishes per caloric group (low, medium, high)
     *
     * in this case we are using a custom DTO
     *
     * @return
     */
    public Iterable<DishesPerCaloricCategory> reportDishesPerCaloricCategory() {
        return repo.reportDishesPerCaloricCategory();
    }

    /**
     * reports the number of dishes per caloric group (low, medium, high)
     *
     * in this case we are returning the data as standard tuples (arrays of
     * objects)
     *
     * @return
     */
    public Iterable<Object[]> reportDishesPerCaloricCategoryAsTuples() {
        return repo.reportDishesPerCaloricCategoryAsTuples();

    }
}
