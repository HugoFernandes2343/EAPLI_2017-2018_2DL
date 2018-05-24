/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.meal.ConsultMealRatingController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.ratings.Comment;
import eapli.ecafeteria.domain.ratings.MealRating;
import eapli.ecafeteria.domain.ratings.Score;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Santiago <1160696@isep.ipp.pt>
 */
public class ConsultMealRatingUI extends AbstractUI { // POR ACABAR
    
    private ConsultMealRatingController cmrc = new ConsultMealRatingController();
    
    private static final int CONTINUE = 1;
    private static final int STOP = 2;
    
    private int CONFIRM = 1;
    private int REPLY = 2;
    
    @Override
    protected boolean doShow() {
//        final Iterable<Meal> meals = cmrc.allMeals();
        Iterable<MealRating> ratings;
        int ratingsCount = 0;
        String reply;
        Comment com;
        Score score;
        System.out.println(headline() + "\n\n");
        List<Reservation> reserv = (List<Reservation>) cmrc.allReservations();
        if (reserv.isEmpty()) {
            System.out.println("No reservations available at the moment.");
            return false;
        } else {
            SelectWidget<Reservation> reservChooser = new SelectWidget<>("Reservations", reserv);
            reservChooser.show();
            Reservation choosenReserv = reservChooser.selectedElement();
            
            
            if(choosenReserv == null){
                return false;
            }
            
            ratings = cmrc.allRatings(choosenReserv);
            if(ratings.iterator().hasNext() == true){
                for(MealRating ra : ratings){
                    com = ra.comment("    ");
                    System.out.println("Rating: " + ra.ratingNumber());
                    System.out.println(com);
                    System.out.println("   "  + com.reply());
                    score = ra.ratingNumber();
                    ratingsCount++;
                }
                System.out.println("\nNumber of Rates for this Meal: " + ratingsCount);
                    if (ratingsCount != 0) {
                        System.out.println("Rating Score: " + "\n\n\n");
                    } else {
                        System.out.println("\nNo score for this meal.");
                    }

                    SelectWidget<Integer> rep = new SelectWidget<>("\n(1-Yes)Do you want to reply a comment?(2-No)", oneTwo());
                    rep.show();
                    REPLY = rep.selectedOption();

                    
                    
                    
                    
                    if (REPLY == 1) {
                        SelectWidget<MealRating> cchooser = new SelectWidget<>("Comments of Ratings:", ratings);
                        cchooser.show();
                        MealRating commentChoosen = cchooser.selectedElement();
                        if (commentChoosen == null) {
                            return false;
                        }

                        System.out.println("Reply: ");
                        Scanner scanIn = new Scanner(System.in);
                        reply = scanIn.nextLine();
                        
                        try {
                            cmrc.answerToComment(commentChoosen, reply);
                        } catch (DataConcurrencyException ex) {
                            Logger.getLogger(ConsultMealRatingUI.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (DataIntegrityViolationException ex) {
                            Logger.getLogger(ConsultMealRatingUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    System.out.println("There are no rates associated with that meal yet");
                }
            
                SelectWidget<Integer> confirm = new SelectWidget<>("\n(1-Yes)Do you want to see another rating of some meal?(2-No)", oneTwo());
                confirm.show();
                CONFIRM = confirm.selectedOption();
        
            }
        return false;
    }

    private ArrayList<Integer> oneTwo() {
        ArrayList<Integer> oneTwo = new ArrayList<>();

        
        
        oneTwo.add(CONTINUE);
        oneTwo.add(STOP);

        
        
        return oneTwo;
    }

    @Override
    public String headline() {
        return "CONSULT MEAL RATING";
    }
    
}