/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteriashift.CafeteriaShift;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author hugod
 */
public interface CafeteriaShiftRepository extends DataRepository<CafeteriaShift,Long> {
    
    public CafeteriaShift findCafeteriaShift();
    
}
