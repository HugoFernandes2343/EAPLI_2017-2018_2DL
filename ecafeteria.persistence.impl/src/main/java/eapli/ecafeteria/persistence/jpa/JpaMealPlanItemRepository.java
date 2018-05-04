/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menu.MenuState;
import eapli.ecafeteria.persistence.MealPlanItemRepository;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author Paulo Jorge
 */
public class JpaMealPlanItemRepository extends CafeteriaJpaRepositoryBase<MealPlanItem, Long> implements MealPlanItemRepository {

    @Override
    public Iterable<MealPlanItem> getMealPlanItemList(MealPlan mealPlan) {
        final Query q;
        String where = "e.mealPlan=:mealPlan";
        q = entityManager().createQuery("SELECT e FROM MealPlanItem e WHERE " + where, this.entityClass);
        q.setParameter("mealPlan", mealPlan);
        return q.getResultList();
    }

    @Override
    public MealPlanItem findByIDMealPlanItem(Long id) {
        Optional<MealPlanItem> m = matchOne("e.id=:id", "id", id);

        if (m.isPresent()) {
            MealPlanItem ms = m.get();
            return ms;
        } else {
            return null;
        }
    }

    @Override
    public void updateTable(Long id, int quantidade) {
        final Query q;
        q = entityManager().createQuery("update MealPlanItem set DishQuantity = quantidade where id=:id", this.entityClass);
        q.setParameter("quantidade", quantidade);
        q.setParameter("id", id);

    }

}
