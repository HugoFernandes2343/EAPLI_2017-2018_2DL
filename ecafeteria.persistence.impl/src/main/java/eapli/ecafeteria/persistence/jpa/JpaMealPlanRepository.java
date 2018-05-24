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
import eapli.framework.domain.Designation;
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
        q = entityManager().createQuery("SELECT e FROM MealPlan e WHERE " + where, this.entityClass);
        q.setParameter("mealPlanState", MealPlanState.IN_PROGRESS);
        return q.getResultList();
    }

    @Override
    public MealPlan findByIDMealPlan(Designation name) {

        Optional<MealPlan> mp = matchOne("e.name=:name", "name", name);
        if (mp.isPresent()) {
            MealPlan ms = mp.get();
            return ms;
        } else {
            return null;
        }
    }

    @Override
    public List<MealPlanItem> getMealPlanItemsFromMealPlan(MealPlan mealPlan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
