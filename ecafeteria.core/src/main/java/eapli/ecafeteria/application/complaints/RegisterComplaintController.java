package eapli.ecafeteria.application.complaints;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.complaints.Complaint;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.ComplaintRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.util.Optional;

/**
 * @author <1160777@isep.ipp.pt>Marco Carneiro</1160777@isep.ipp.pt>
 */
public class RegisterComplaintController implements Controller {

    private final ComplaintRepository cr = PersistenceContext.repositories().complaints();
    private final ReservationRepository rrp = PersistenceContext.repositories().reservations();
    private final CafeteriaUserService userService = new CafeteriaUserService();

    /**
     * Formulates a complaint
     *
     * @param isUserShown is the user shown or is it anonymous
     * @param mealCode    meal Code is in fact the reservation code
     * @param description
     */
    public void formulateComplaint(boolean isUserShown, String mealCode, String title, String description) throws DataConcurrencyException, DataIntegrityViolationException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);

        Complaint complaintToAdd;
        Meal mealToAdd;
        Optional<CafeteriaUser> userToAdd;

        if (isUserShown && !mealCode.equals("Not Specified")) {               //All data is provided (User,Meal,title,description)
            userToAdd = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
            mealToAdd = rrp.findByCode(mealCode).get().meal();
            complaintToAdd = new Complaint(userToAdd.get(), mealToAdd, title, description);
        } else if (isUserShown && mealCode.equals("Not Specified")) {          //All data except meal data is provided
            userToAdd = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
            complaintToAdd = new Complaint(userToAdd.get(), title, description);
        } else if (!isUserShown && !mealCode.equals("Not Specified")) {        //No user data is provided however meal data is
            mealToAdd = rrp.findByCode(mealCode).get().meal();
            complaintToAdd = new Complaint(mealToAdd, title, description);
        } else {                                                              //No data is provided (title, description)
            complaintToAdd = new Complaint(title, description);
        }
        cr.save(complaintToAdd);
    }

}
