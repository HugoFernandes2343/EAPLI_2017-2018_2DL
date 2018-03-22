package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.reporting.DishesPerCaloricCategory;
import eapli.ecafeteria.domain.dishes.reporting.DishesPerDishType;
import eapli.framework.persistence.repositories.ReportingRepository;

/**
 *
 * @author PAG
 */
public interface DishReportingRepository extends ReportingRepository {

    public Iterable<DishesPerDishType> dishesPerDishType();

    public Iterable<Dish> reportHighCaloriesDishes();

    public Iterable<DishesPerCaloricCategory> reportDishesPerCaloricCategory();

    public Iterable<Object[]> reportDishesPerCaloricCategoryAsTuples();
}
