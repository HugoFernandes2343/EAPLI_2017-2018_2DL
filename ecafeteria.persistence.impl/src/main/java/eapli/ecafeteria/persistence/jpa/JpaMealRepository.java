/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menu.MenuState;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Hugo
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository {

    @Override
    public Iterable<Meal> findMealByMenu(Menu menu) {
        final Query q;
        String where = "e.menu=:menu";
        q = entityManager().createQuery("SELECT e FROM Meal e WHERE " + where, this.entityClass);
        q.setParameter("menu", menu);
        return q.getResultList();
    }

    @Override
    public Iterable<Meal> findMealsByDateAndMealType(Calendar d, MealType type) {
        final Query q = entityManager().createQuery("SELECT m FROM Meal m WHERE m.date=:dt AND m.mealType=:tp", this.entityClass);
        q.setParameter("dt", d);
        q.setParameter("tp", type);
        return q.getResultList();
    }

    @Override
    public Iterable<Meal> findMealByDate(Calendar d) {
       final Query q = entityManager().createQuery("SELECT m FROM Meal m WHERE m.date=:dt", this.entityClass);
        q.setParameter("dt", d);
        return q.getResultList();
    }

    @Override
    public List<Meal> getMealsUsed(Lot lot) {
        final Query q;
        q = entityManager().createNativeQuery("SELECT * FROM Meal m WHERE m.pk IN (SELECT up.meal_pk FROM Used_Lot up WHERE up.lotlist_pk IN (SELECT l.pk FROM lot l WHERE l.code=:codigo))");
        q.setParameter("codigo", lot.getCode());

        System.out.println("Teste"+q.getResultList());
        return q.getResultList();
    }
}
