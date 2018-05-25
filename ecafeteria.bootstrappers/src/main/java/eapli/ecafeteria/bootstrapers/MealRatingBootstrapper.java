///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package eapli.ecafeteria.bootstrapers;
//
//import eapli.ecafeteria.application.meal.ConsultMealRatingController;
//import eapli.ecafeteria.domain.authz.Username;
//import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
//import eapli.ecafeteria.domain.meals.Meal;
//import eapli.ecafeteria.domain.reservations.Reservation;
//import eapli.ecafeteria.persistence.CafeteriaUserRepository;
//import eapli.ecafeteria.persistence.MealRatingRepository;
//import eapli.ecafeteria.persistence.MealRepository;
//import eapli.ecafeteria.persistence.PersistenceContext;
//import eapli.ecafeteria.persistence.ReservationRepository;
//import eapli.framework.actions.Action;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author João Santiago <1160696@isep.ipp.pt>
// */
//public class MealRatingBootstrapper implements Action{
//    
//    private final MealRatingRepository mmr = PersistenceContext.repositories().ratings();
//    private final ConsultMealRatingController cmrc = new ConsultMealRatingController();
//    private final MealRepository mr = PersistenceContext.repositories().meals();
//    private final CafeteriaUserRepository cur = PersistenceContext.repositories().cafeteriaUsers();
//    private final ReservationRepository rr = PersistenceContext.repositories().reservations();
//
//    @Override
//    public boolean execute() {
//        
//        ArrayList<Reservation> list = new ArrayList<>();
//        Meal m = null;
//        list = (ArrayList<Reservation>) rr.findByMeal(m);
//        
//        try{
//            Username nm = new Username("900330");
//            CafeteriaUser us = cur.findByUsername(nm).get();
//            Reservation rs1 = new Reservation("1", m, us);
//            Reservation rs2 = new Reservation("2", m, us);
//            Reservation rs3 = new Reservation("3", m, us);
//            Reservation rs4 = new Reservation("4", m, us);
//            Reservation rs5 = new Reservation("5", m, us);
//            
//            rs1.meal();
//            rs2.meal();
//            rs3.meal();
//            
//            cmrc.
//        }
//        
//    }
//    
//    
//}
