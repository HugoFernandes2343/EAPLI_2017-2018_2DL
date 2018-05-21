/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.reservations;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.movement.MovementBuilder;
import eapli.ecafeteria.domain.movement.MovementDescription;
import eapli.ecafeteria.domain.movement.Refund;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.application.Controller;
import eapli.framework.domain.ReservationStateViolationException;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;

/**
 *
 * @author 1161569
 */
public class CancelReservationController implements Controller{
    
    private final ReservationRepository reservationRepo = PersistenceContext.repositories().reservations();
    private final ListReservationService listReservationService = new ListReservationService();
    private final MovementRepository movementRepo = PersistenceContext.repositories().movements();
    private final CafeteriaUserService userService = new CafeteriaUserService();
    
    
    public Iterable<Reservation> findBooked(){
       return listReservationService.findBooked();
    }
    
    public void cancelReservation(Money value, Reservation reserv) throws ReservationStateViolationException, DataConcurrencyException, DataIntegrityViolationException{
        reserv.cancel();
        final MovementBuilder movementBuilder = new MovementBuilder();
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        movementBuilder.withCafeteriaUser(user.get()).withDescriptionAndMoney(MovementDescription.REFUND, value);
        Refund mov = (Refund) movementBuilder.build();
        movementRepo.save(mov);
        reservationRepo.save(reserv);
    }
}
