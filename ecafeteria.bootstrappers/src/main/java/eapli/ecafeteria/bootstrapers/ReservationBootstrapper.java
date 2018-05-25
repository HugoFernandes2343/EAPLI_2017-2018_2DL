/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.reservations.RegisterReservationController;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.domain.ReservationStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Norberto Sousa - 1120608
 */
public class ReservationBootstrapper implements Action {

    private final RegisterReservationController rrCont = new RegisterReservationController();
    private final MealRepository mRP = PersistenceContext.repositories().meals();
    private final CafeteriaUserRepository usRP = PersistenceContext.repositories().cafeteriaUsers();

    @Override
    public boolean execute() {
        ArrayList<Meal> list = new ArrayList<>();
        Calendar tomorrow = DateTime.now();
        tomorrow.add(Calendar.DATE, 1);
        list = (ArrayList<Meal>) mRP.findMealsByDateAndMealType(tomorrow, new MealType(MealType.MealTypes.LUNCH));
        Meal m1 = null;
        for (Meal m : list) {
            if (m.dish().is(Designation.valueOf("Hamburger"))) {
                m1 = m;
            }
        }

        try {
            Username nm = new Username("900330");
            CafeteriaUser us = usRP.findByUsername(nm).get();
            Reservation rs1 = new Reservation("1", m1, us);
            Reservation rs2 = new Reservation("2", m1, us);
            Reservation rs3 = new Reservation("3", m1, us);
            Reservation rs4 = new Reservation("4", m1, us);
            Reservation rs5 = new Reservation("5", m1, us);
            
            rs1.deliver();
            rs2.deliver();
            rs3.deliver();

            rrCont.registerReservation(rs1);
            rrCont.registerReservation(rs2);
            rrCont.registerReservation(rs3);
            rrCont.registerReservation(rs4);
            rrCont.registerReservation(rs5);
            
        } catch (DataConcurrencyException | DataIntegrityViolationException | NullPointerException | ReservationStateViolationException ex) {
            System.out.println("Erro a guardar reservations");
        }
        return true;
    }

}
