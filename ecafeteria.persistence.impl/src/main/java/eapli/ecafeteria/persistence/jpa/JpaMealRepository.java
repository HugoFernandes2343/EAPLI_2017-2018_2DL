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
import javax.persistence.Query;

/**
 *
 * @author Hugo
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository {

    @Override
    public Iterable<Meal> findMealOneMenu(long id) {
        final Query q;
        String where = "e.menuID=:menuID";
        q = entityManager().createQuery("SELECT e FROM Menu e WHERE " + where, this.entityClass);
        q.setParameter("menuID", id);
        return q.getResultList();
    }
}
