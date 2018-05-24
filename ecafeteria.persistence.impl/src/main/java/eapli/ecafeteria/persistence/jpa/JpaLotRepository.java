/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.persistence.LotRepository;

/**
 *
 * @author Hugo
 */
public class JpaLotRepository extends CafeteriaJpaRepositoryBase<Lot, String> implements LotRepository {

    public JpaLotRepository() {
    }
    
}
