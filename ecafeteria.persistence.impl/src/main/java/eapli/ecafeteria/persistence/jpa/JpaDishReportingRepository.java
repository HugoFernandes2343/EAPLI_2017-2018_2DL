/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import javax.persistence.TypedQuery;

import eapli.ecafeteria.domain.dishes.reporting.DishesPerDishType;
import eapli.ecafeteria.persistence.DishReportingRepository;

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
                "SELECT new eapli.ecafeteria.domain.dishes.reporting.DishesPerDishType(t.acronym, COUNT(d)) FROM Dish d, DishType t WHERE d.dishType = t GROUP BY d.dishType",
                DishesPerDishType.class);

        return query.getResultList();
    }
}
