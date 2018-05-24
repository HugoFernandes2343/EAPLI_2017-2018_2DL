/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser.profile;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfileField;
import eapli.ecafeteria.persistence.NutritionalProfileRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Optional;

/**
 *
 * @author Rodrigo Soares <1140420@isep.ipp.pt>
 */
public class EditNutritionalProfileController implements Controller{
    private Optional<CafeteriaUser> user;
    private Optional<NutritionalProfile> nutriProfile;
    private NutritionalProfileRepository nutriProfileRepo;
    
    public Iterable<NutritionalProfileField> findEditableFields(){
        CafeteriaUserService userService = new CafeteriaUserService();
        user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        
        //nutriProfileRepo = PersistenceContext.repositories().nutritionalProfiles();
        //nutriProfile = nutriProfileRepo.findByUsername(user.get().user().username());
        
        return user.get().getNutritionalProfile().getFields();
    }
    
    public boolean updateNutritionalField (NutritionalProfileField chosenField){
        return nutriProfileRepo.updateField(chosenField);
    }
}
