/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.dishes.reporting.DishesPerDishType;
import eapli.ecafeteria.persistence.DishReportingRepository;
import javax.persistence.TypedQuery;

/**
 *
 * @author pgsou_000
 */
public class JpaDishReportingRepository extends CafeteriaJpaRepositoryBase implements DishReportingRepository {

    @Override
    public Iterable<DishesPerDishType> dishesPerDishType() {

        final TypedQuery<DishesPerDishType> query = entityManager()
                .createQuery("SELECT new eapli.ecafeteria.domain.dishes.reporting.DishesPerDishType(t.acronym, COUNT(d)) FROM Dish d, DishType t WHERE d.dishType = t GROUP BY d.dishType", DishesPerDishType.class);

        return query.getResultList();
    }
}
