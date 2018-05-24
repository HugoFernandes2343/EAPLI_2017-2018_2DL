/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.LotRepository;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author Hugo
 */
public class JpaLotRepository extends CafeteriaJpaRepositoryBase<Lot, String> implements LotRepository {

    public JpaLotRepository() {
    }


    @Override
    public List<Meal> getMealsUsed(Lot lot) {
        //SELECT m FROM meal m WHERE m.pk IN (
        //SELECT up.meal_pk FROM usedlot up WHERE up.lotlist_pk IN (
        //(SELECT l.pk FROM lot l WHERE l.code =:variavelAqui)
        //)
        //)
        final Query q;
        q = entityManager().createQuery("SELECT m FROM Meal m WHERE m.pk IN (SELECT up.meal_pk FROM Used_Lot up WHERE up.lotlist_pk IN (SELECT l.pk FROM lot l WHERE l.code=:codigo))");
        q.setParameter("codigo", lot.getClass());
        return q.getResultList();
    }
}
