/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanState;
import eapli.ecafeteria.persistence.MealPlanRepository;
import javax.persistence.Query;

/**
 *
 * @author Paulo Jorge
 */
public class JpaMealPlanRepository extends CafeteriaJpaRepositoryBase<MealPlan, Integer> implements MealPlanRepository {

    @Override
    public Iterable<MealPlan> findAllMealPlanInProgress() {
        final Query q;
        String where = "e.mealPlanState=:mealPlanState";
        q = entityManager().createQuery("SELECT e FROM Menu e WHERE " + where, this.entityClass);
        q.setParameter("mealPlanState", MealPlanState.IN_PROGRESS);
        return q.getResultList();
    }

}
