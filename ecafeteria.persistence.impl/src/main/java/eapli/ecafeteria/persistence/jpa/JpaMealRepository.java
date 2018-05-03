/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menu.MenuState;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.util.DateTime;
import javax.persistence.Query;

/**
 *
 * @author Hugo
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository {

    @Override
    public Iterable<Meal> findMealOneMenu(Menu menu) {
        final Query q;
        String where = "e.menu=:menu";
        q = entityManager().createQuery("SELECT e FROM Meal e WHERE " + where, this.entityClass);
        q.setParameter("menu", menu);
        return q.getResultList();
    }

    @Override
    public Iterable<Meal> findMealsByDateAndMealType(DateTime d, String type) {
        final Query q = entityManager().createQuery("SELECT m FROM Meal m WHERE m.date=:dt AND m.mealType:=tp", this.entityClass);
        q.setParameter("dt", d);
        q.setParameter("tp", type);
        return q.getResultList();
    }

}
