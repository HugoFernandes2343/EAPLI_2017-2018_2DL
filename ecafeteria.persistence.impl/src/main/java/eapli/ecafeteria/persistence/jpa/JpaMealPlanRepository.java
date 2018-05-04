/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.kitchen.MealPlanState;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MealPlanRepository;
import java.util.List;
import java.util.Optional;
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

    @Override
    public List<MealPlanItem> getMealPlanItemsFromMealPlan(MealPlan mealPlan) {
        final Query q;
        String where = "e.mealPlan=:mealPlan";
        q = entityManager().createQuery("SELECT e FROM MealPlan e WHERE " + where, this.entityClass);
        q.setParameter("mealPlanItem", MealPlanState.IN_PROGRESS);
        return q.getResultList();
    }

}
