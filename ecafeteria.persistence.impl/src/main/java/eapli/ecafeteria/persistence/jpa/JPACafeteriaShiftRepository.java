/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteriashift.CafeteriaShift;
import eapli.ecafeteria.persistence.CafeteriaShiftRepository;
import javax.persistence.Query;

/**
 *
 * @author hugod
 */
public class JPACafeteriaShiftRepository extends CafeteriaJpaRepositoryBase<CafeteriaShift, Long> implements CafeteriaShiftRepository {

    @Override
    public CafeteriaShift findCafeteriaShift() {
        Query createQuery = entityManager().createQuery("SELECT cf FROM CafeteriaShift cf",this.entityClass);
        return (CafeteriaShift) createQuery.getSingleResult();

    }
}
