/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

import java.util.List;

/**
 *
 * @author Hugo
 */
public class InMemoryLotRepository extends InMemoryRepository<Lot, String> implements LotRepository {

    public InMemoryLotRepository() {
    }

    @Override
    protected String newKeyFor(Lot entity) {
        return entity.id();
    }

}
