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
    //private Optional<NutritionalProfile> nutriProfile;
    //private NutritionalProfileRepository nutriProfileRepo;
    private CafeteriaUserService userService;
    
    public CafeteriaUser findUser(){
        userService = new CafeteriaUserService();
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        
        //nutriProfileRepo = PersistenceContext.repositories().nutritionalProfiles();
        //nutriProfile = nutriProfileRepo.findByUsername(user.get().user().username());
        
        return user.get();
    }
    
    public void updateNutritionalProfileField (CafeteriaUser user, NutritionalProfileField newField){
        user.updateNutritionalProfileFieldValue(newField);
        
        userService.updateUser(user);
    }
}
