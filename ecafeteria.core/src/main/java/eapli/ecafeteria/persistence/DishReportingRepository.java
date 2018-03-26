package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.reporting.dishes.DishesPerCaloricCategory;
import eapli.ecafeteria.reporting.dishes.DishesPerDishType;
import eapli.framework.persistence.repositories.ReportingRepository;

/**
 *
 * @author PAG
 */
public interface DishReportingRepository extends ReportingRepository {

    public Iterable<DishesPerDishType> dishesPerDishType();

    public Iterable<Dish> reportHighCaloriesDishes(Integer highCalories);

    public Iterable<DishesPerCaloricCategory> reportDishesPerCaloricCategory();

    public Iterable<Object[]> reportDishesPerCaloricCategoryAsTuples();
}
