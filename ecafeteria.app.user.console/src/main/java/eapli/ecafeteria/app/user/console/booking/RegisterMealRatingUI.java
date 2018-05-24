/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.booking;

import eapli.ecafeteria.application.meal.RegisterMealRatingController;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class RegisterMealRatingUI extends AbstractUI {

    private final RegisterMealRatingController rmrController = new RegisterMealRatingController();

    protected Controller controller() {
        return this.rmrController;
    }

    @Override
    protected boolean doShow() {
        System.out.println("User list reservations: \n");
        CafeteriaUser user = rmrController.getUser();
        List<Reservation> lst = rmrController.lstReservations(user);
        for (Reservation reservation : lst) {
            System.out.println(reservation.toString());
        }
        String code = Console.readLine("Choose one, using the code of reservation: ");

        Reservation r = rmrController.selectReservation(code);
        int rating = -1;
        while (rating < 1 || rating > 5) {
            rating = Console.readInteger("Insert rating number: (1 to 5)");
        }

        String isComment = "";
        String comment = "";

        while (isComment.compareToIgnoreCase("Y") != 0 && isComment.compareToIgnoreCase("N") != 0) {
            isComment = Console.readLine("Add comment? (Y/N)");
            System.out.println(isComment);
        }
        if (isComment.equalsIgnoreCase("N")) {
            rmrController.registerMealRating(r, rating);
        } else {
            while (comment.compareTo("") == 0) {
                comment = Console.readLine("Comment: ");
                rmrController.registerMealRatingWithComment(r, rating, comment);
            }
        }
        try {
            rmrController.saveMealRating();
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(RegisterMealRatingUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public String headline() {
        return ("Register Meal Rating");
    }

}
