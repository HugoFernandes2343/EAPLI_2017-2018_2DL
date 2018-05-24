/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.reservations;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * MealDeliveryRegistrationController class
 * Created on 20/04/2018
 * 
 * @author 1160777 & 1161569
 */
public class MealDeliveryRegistrationController implements Controller{
    
    private final ReservationRepository rrp = PersistenceContext.repositories().reservations();
    
    /**
     * Searches for a given Reservation by code
     * 
     * @param code code that identifies the Reservation
     * @return Reservation
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     */
    public Reservation acquireReservation(String code) throws DataConcurrencyException, DataIntegrityViolationException{
        
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SALE);
        
        if (code == null) {
            throw new IllegalArgumentException();
        }
        
        return this.rrp.findByCode(code).get();
    }
   
    /**
     * Confirms the save
     * @param res 
     * @throws eapli.framework.persistence.DataConcurrencyException 
     * @throws eapli.framework.persistence.DataIntegrityViolationException 
     */
    public void confirmDelivery(Reservation res) throws DataConcurrencyException, DataIntegrityViolationException{
        rrp.save(res);
    }

}
