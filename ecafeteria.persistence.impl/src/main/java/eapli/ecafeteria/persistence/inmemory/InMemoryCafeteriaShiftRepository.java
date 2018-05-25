/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteriashift.CafeteriaShift;
import eapli.ecafeteria.persistence.CafeteriaShiftRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
public class InMemoryCafeteriaShiftRepository extends InMemoryRepositoryWithLongPK<CafeteriaShift> implements CafeteriaShiftRepository {

    @Override
    protected Long newKeyFor(CafeteriaShift entity) {
        return entity.id();
    }

    @Override
    public CafeteriaShift findCafeteriaShift() {
        return first();
    }

}
