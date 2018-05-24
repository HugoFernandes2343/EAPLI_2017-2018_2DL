/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author 1161569
 */
public class RegisterLotController implements Controller {
    
    private final LotRepository lotRepo = PersistenceContext.repositories().lots();
    
    public void registerLot(String code,Material material) throws DataConcurrencyException, DataIntegrityViolationException {
        Lot lot=new Lot(code,material);
        lotRepo.save(lot);
    }
    
}
