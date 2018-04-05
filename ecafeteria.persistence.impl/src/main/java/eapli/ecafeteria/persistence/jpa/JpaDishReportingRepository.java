/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.reporting.dishes.DishesPerCaloricCategory;
import eapli.ecafeteria.reporting.dishes.DishesPerDishType;
import eapli.ecafeteria.persistence.DishReportingRepository;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author pgsou_000
 */
@SuppressWarnings("rawtypes")
public class JpaDishReportingRepository extends CafeteriaJpaRepositoryBase
        implements DishReportingRepository {

    @Override
    public Iterable<DishesPerDishType> dishesPerDishType() {

        final TypedQuery<DishesPerDishType> query = entityManager().createQuery(
                "SELECT new eapli.ecafeteria.reporting.dishes.DishesPerDishType(t.acronym, COUNT(d)) FROM Dish d, DishType t WHERE d.dishType = t GROUP BY d.dishType",
                DishesPerDishType.class);

        return query.getResultList();
    }

    @Override
    public Iterable<Dish> reportHighCaloriesDishes(Integer highCalories ) {
        final TypedQuery<Dish> query = entityManager()
                .createQuery("SELECT d FROM Dish d WHERE d.nutricionalInfo.calories > :hc", Dish.class);
        query.setParameter("hc", highCalories);
        return query.getResultList();
    }

    private static final String DISHES_PER_CALORIC_CATEGORY_QUERY = "SELECT caloricCategory, COUNT(*) as n "
            + "FROM (SELECT *, CASE WHEN calories <= 150 THEN 'low' WHEN calories > 150 AND calories < 350 THEN 'medium' ELSE 'high' END AS caloricCategory FROM DISH) "
            + "GROUP BY caloricCategory";

    @SuppressWarnings("unchecked")
    @Override
    public Iterable<DishesPerCaloricCategory> reportDishesPerCaloricCategory() {
        final Query query = entityManager().createNativeQuery(DISHES_PER_CALORIC_CATEGORY_QUERY, "DishesPerCaloricCategoryMapping");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterable<Object[]> reportDishesPerCaloricCategoryAsTuples() {
        final Query query = entityManager().createNativeQuery(DISHES_PER_CALORIC_CATEGORY_QUERY);
        return query.getResultList();
    }
}
