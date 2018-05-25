package eapli.ecafeteria.app.user.console.complaint;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.application.complaints.RegisterComplaintController;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.Console;

/**
 * @author <1160777@isep.ipp.pt>Marco Carneiro</1160777@isep.ipp.pt>
 */
public class RegisterComplaintUI extends CafeteriaUserBaseUI {

    private final RegisterComplaintController rcc = new RegisterComplaintController();
    private final CafeteriaUserService userService = new CafeteriaUserService();

    @Override
    protected boolean doShow() {
        boolean user = false, reservationExists = true, wishToTryAgain = true;
        String code = "[PH]";
        String tryConfirmation = "";
        System.out.print("==>>    File a Complaint    <<==\n");

        final String title = Console.readLine("Title : \n");
        final String description = Console.readLine("Description (Max 255 char): \n");

        final String userConfirmation = Console.readLine("Do you want your user information to be bound to this complaint?(Type 1 for yes, any for no)");
        if (userConfirmation.equals("1")) {
            user = true;
        }
        do {//Do while loop + try catch to verify that the code is valid
            try {
                final String mealConfirmation = Console.readLine("Is it referred to a specific booked Meal?(Type 1 for yes, any for no)");
                if (mealConfirmation.equals("1")) {
                    final String complainMeal = Console.readLine("Insert the reservation code please :\n");
                    code = complainMeal;
                } else {
                    code = "Not Specified";
                }
            } catch (Exception ex) {
                reservationExists = false;
                System.out.println("Error -> " + ex.getMessage());
                tryConfirmation = Console.readLine("\n>>Invalid code, Do you want to try again?<<(Type 1 for yes, any for no)");
            } finally {
                if (!tryConfirmation.equals("1")) {
                    wishToTryAgain = false;
                }
            }
            if (!wishToTryAgain) {
                break;
            }
        } while (!reservationExists);

        try {
            rcc.formulateComplaint(user, code, title, description);
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            System.out.println("Error -> " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }
}
